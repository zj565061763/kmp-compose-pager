package com.sd.demo.kmp.compose_pager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RouteHome(
  onClickSamplePagerState: () -> Unit,
  onClickSamplePagerLoop: () -> Unit,
  onClickSampleInfiniteHorizontalPager: () -> Unit,
) {
  Scaffold { padding ->
    Column(
      modifier = Modifier.fillMaxSize().padding(padding),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Button(onClick = onClickSamplePagerState) { Text(text = "SamplePagerState") }
      Button(onClick = onClickSamplePagerLoop) { Text(text = "SamplePagerLoop") }
      Button(onClick = onClickSampleInfiniteHorizontalPager) { Text(text = "SampleInfiniteHorizontalPager") }
    }
  }
}