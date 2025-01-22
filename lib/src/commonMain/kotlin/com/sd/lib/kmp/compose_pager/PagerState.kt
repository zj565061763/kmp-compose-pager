package com.sd.lib.kmp.compose_pager

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow

/**
 * 监听[PagerState.currentPage]
 */
@Composable
fun <T : PagerState> T.FOnCurrentPage(onChange: T.(Int) -> Unit) {
  val state = this
  val onChangeUpdated by rememberUpdatedState(onChange)
  LaunchedEffect(state) {
    snapshotFlow { state.currentPage }
      .collect { onChangeUpdated(it) }
  }
}

/**
 * 监听[PagerState.settledPage]
 */
@Composable
fun <T : PagerState> T.FOnSettledPage(onChange: T.(Int) -> Unit) {
  val state = this
  val onChangeUpdated by rememberUpdatedState(onChange)
  LaunchedEffect(state) {
    snapshotFlow { state.settledPage }
      .collect { onChangeUpdated(it) }
  }
}