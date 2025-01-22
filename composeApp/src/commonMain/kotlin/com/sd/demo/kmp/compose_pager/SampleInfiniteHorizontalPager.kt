package com.sd.demo.kmp.compose_pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sd.lib.kmp.compose_pager.FLoopPlay
import com.sd.lib.kmp.compose_pager.FOnSettledPage
import com.sd.lib.kmp.compose_pager.rememberInfinitePagerState

@Composable
fun SampleInfiniteHorizontalPager(
  onClickBack: () -> Unit,
) {
  val state = rememberInfinitePagerState { 3 }
  state.FOnSettledPage { logMsg { "FOnSettledPage:${realPage(it)}" } }
  state.FLoopPlay()

  RouteScaffold(
    title = "SampleInfiniteHorizontalPager",
    onClickBack = onClickBack,
  ) {
    HorizontalPager(
      state = state,
      modifier = Modifier.fillMaxSize(),
    ) {
      val page = state.realPage(it)
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        Text(text = page.toString())
      }
    }
  }
}