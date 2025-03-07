package com.sd.lib.kmp.compose_pager

import androidx.annotation.FloatRange
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberInfinitePagerState(
  initialPage: Int = 0,
  @FloatRange(from = -0.5, to = 0.5) initialPageOffsetFraction: Float = 0f,
  pageCount: () -> Int,
): InfinitePagerState {
  return rememberSaveable(saver = InfinitePagerState.Saver) {
    val realCount = pageCount()
    val infiniteCount = getInfiniteCount(realCount)
    InfinitePagerState(
      currentPage = if (realCount != infiniteCount) infiniteCount / 2 + initialPage else initialPage,
      currentPageOffsetFraction = initialPageOffsetFraction,
      updatedPageCount = pageCount,
    )
  }.apply {
    pageCountState.value = pageCount
  }
}

class InfinitePagerState internal constructor(
  currentPage: Int,
  currentPageOffsetFraction: Float,
  updatedPageCount: () -> Int,
) : PagerState(currentPage, currentPageOffsetFraction) {

  internal val pageCountState = mutableStateOf(updatedPageCount)

  override val pageCount: Int get() = getInfiniteCount(realPageCount)

  val realPageCount: Int get() = pageCountState.value.invoke()

  fun realPage(page: Int): Int {
    val realCount = realPageCount
    return if (realCount <= 1) page else page % realCount
  }

  companion object {
    internal val Saver: Saver<InfinitePagerState, *> = listSaver(
      save = {
        listOf(
          it.currentPage,
          (it.currentPageOffsetFraction).coerceIn(-0.5f, 0.5f),
          it.realPageCount,
        )
      },
      restore = {
        InfinitePagerState(
          currentPage = it[0] as Int,
          currentPageOffsetFraction = it[1] as Float,
          updatedPageCount = { it[2] as Int },
        )
      }
    )
  }
}

private fun getInfiniteCount(realCount: Int): Int {
  if (realCount <= 1) return realCount

  val multi = Int.MAX_VALUE / realCount
  if (multi <= 1) return realCount

  val evenMulti = if (multi % 2 == 0) multi else multi - 1
  return evenMulti * realCount
}