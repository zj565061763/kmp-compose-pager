package com.sd.lib.kmp.compose_pager

import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlin.coroutines.cancellation.CancellationException

/**
 * 轮播
 */
@Composable
fun PagerState.FLoopPlay(
  getInterval: () -> Long = { 5000 },
  getNextPage: PagerState.() -> Int = {
    val page = currentPage
    if (page >= pageCount - 1) 0 else page + 1
  },
  scrollToPage: suspend PagerState.(Int) -> Unit = { nextPage ->
    animateScrollToPage(page = nextPage, animationSpec = tween(500))
  },
) {
  if (pageCount <= 1) return

  val isDragged by interactionSource.collectIsDraggedAsState()
  if (isDragged) return

  val getIntervalUpdated by rememberUpdatedState(getInterval)
  val getNextPageUpdated by rememberUpdatedState(getNextPage)
  val scrollToPageUpdated by rememberUpdatedState(scrollToPage)

  val state: PagerState = this
  val lifecycleOwner = LocalLifecycleOwner.current
  LaunchedEffect(state, lifecycleOwner) {
    while (true) {
      delay(getIntervalUpdated())
      if (!lifecycleOwner.lifecycle.fAtLeastState()) {
        delay(getIntervalUpdated())
      }
      val nextPage = getNextPageUpdated()
      scrollToPageUpdated(nextPage)
    }
  }
}

private suspend fun Lifecycle.fAtLeastState(
  state: Lifecycle.State = Lifecycle.State.STARTED,
): Boolean {
  require(state != Lifecycle.State.DESTROYED)
  if (currentState == Lifecycle.State.DESTROYED) throw CancellationException()
  if (currentState.isAtLeast(state)) return true
  currentStateFlow.first { it.isAtLeast(state) }
  return false
}