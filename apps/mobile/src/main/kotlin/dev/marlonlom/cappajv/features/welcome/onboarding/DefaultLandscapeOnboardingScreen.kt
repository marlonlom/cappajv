/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.welcome.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.ui.theme.CappajvTheme

/**
 * Default landscape onboarding screen composable.
 *
 * @author marlonlom
 *
 * @param onOnboardingFinished Action for continue to home screen button clicked.
 * @param modifier Modifier for this composable.
 */
@Composable
internal fun DefaultLandscapeOnboardingScreen(
  onOnboardingFinished: () -> Unit,
  modifier: Modifier = Modifier
) {
  val pagerState = rememberPagerState { OnboardingItemsData.list.size }
  var continueHomeButtonVisible by remember { mutableStateOf(false) }

  LaunchedEffect(pagerState) {
    snapshotFlow { pagerState.currentPage }.collect { page ->
      continueHomeButtonVisible = page == OnboardingItemsData.list.size - 1
    }
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
      .background(MaterialTheme.colorScheme.background)
      .padding(top = 24.dp, bottom = 16.dp)
  ) {
    Column(
      modifier = modifier.fillMaxWidth(0.75f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      HorizontalPager(
        modifier = Modifier.weight(1f),
        state = pagerState,
        key = { pos -> OnboardingItemsData.list[pos] },
        pageSize = PageSize.Fill
      ) { pos ->
        DefaultLandscapeOnboardingItem(OnboardingItemsData.list[pos])
      }
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .height(10.dp)
      )
      OnboardingDotsIndicator(
        totalDots = OnboardingItemsData.list.size,
        selectedIndex = pagerState.currentPage,
        selectedColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unSelectedColor = MaterialTheme.colorScheme.primaryContainer
      )
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .height(20.dp)
      )
      if (continueHomeButtonVisible) {
        OnboardingFinishedButton(onButtonClicked = onOnboardingFinished)
        Spacer(modifier = Modifier.height(20.dp))
      }
    }

  }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(
  showBackground = true,
  device = Devices.AUTOMOTIVE_1024p,
  widthDp = 720,
  heightDp = 360
)
@Composable
private fun DefaultLandscapeOnboardingScreenPreview() {
  CappajvTheme(dynamicColor = false) {
    DefaultLandscapeOnboardingScreen(onOnboardingFinished = {})
  }
}
