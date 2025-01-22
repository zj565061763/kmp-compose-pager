package com.sd.demo.kmp.compose_pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sd.lib.kmp.compose_pager.FOnCurrentPage
import com.sd.lib.kmp.compose_pager.FOnSettledPage

@Composable
fun SamplePagerState(
  onClickBack: () -> Unit,
) {
  val state = rememberPagerState { 10 }
  state.FOnCurrentPage { logMsg { "FOnCurrentPage:$it" } }
  state.FOnSettledPage { logMsg { "FOnSettledPage:$it" } }

  RouteScaffold(
    title = "SamplePagerState",
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