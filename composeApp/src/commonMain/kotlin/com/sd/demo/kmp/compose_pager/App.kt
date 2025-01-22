package com.sd.demo.kmp.compose_pager

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun App() {
  MaterialTheme {
    val navController = rememberNavController()
    NavHost(
      navController = navController,
      startDestination = AppRoute.Home,
    ) {
      composable<AppRoute.Home> {
        RouteHome(
          onClickSamplePagerState = { navController.navigate(AppRoute.SamplePagerState) },
          onClickSamplePagerLoop = { navController.navigate(AppRoute.SamplePagerLoop) },
          onClickSampleInfiniteHorizontalPager = { navController.navigate(AppRoute.SampleInfiniteHorizontalPager) },
        )
      }
      composable<AppRoute.SamplePagerState> {
        SamplePagerState(onClickBack = { navController.popBackStack() })
      }
      composable<AppRoute.SamplePagerLoop> {
        SamplePagerLoop(onClickBack = { navController.popBackStack() })
      }
      composable<AppRoute.SampleInfiniteHorizontalPager> {
        SampleInfiniteHorizontalPager(onClickBack = { navController.popBackStack() })
      }
    }
  }
}

expect fun logMsg(tag: String = "kmp-compose-pager", block: () -> String)