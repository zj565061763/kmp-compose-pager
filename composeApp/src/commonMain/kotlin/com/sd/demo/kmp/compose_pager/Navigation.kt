package com.sd.demo.kmp.compose_pager

import kotlinx.serialization.Serializable

sealed interface AppRoute {
  @Serializable
  data object Home : AppRoute

  @Serializable
  data object SamplePagerState : AppRoute

  @Serializable
  data object SamplePagerLoop : AppRoute

  @Serializable
  data object SampleInfiniteHorizontalPager : AppRoute
}