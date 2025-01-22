package com.sd.demo.kmp.compose_pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sd.lib.kmp.compose_pager.FLoopPlay
import com.sd.lib.kmp.compose_pager.FOnSettledPage

@Composable
fun SamplePagerLoop(
  onClickBack: () -> Unit,
) {
  val state = rememberPagerState { 10 }
  state.FOnSettledPage { logMsg { "FOnSettledPage:$it" } }
  state.FLoopPlay()

  RouteScaffold(
    title = "SamplePagerLoop",
    onClickBack = onClickBack,
  ) {
    HorizontalPager(
      state = state,
      modifier = Modifier.fillMaxSize(),
    ) { index ->
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        Text(text = index.toString())
      }
    }
  }
}