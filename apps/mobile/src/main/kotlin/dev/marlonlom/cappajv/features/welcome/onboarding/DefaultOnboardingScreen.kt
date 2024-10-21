/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.features.welcome.onboarding

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.ui.theme.CappajvTheme

/**
 * Default portrait onboarding screen composable.
 *
 * @author marlonlom
 *
 * @param onOnboardingFinished Action for onboarding finished event.
 * @param modifier Modifier for this composable.
 */
@Composable
internal fun DefaultOnboardingScreen(
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
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.background)
      .padding(top = 24.dp, bottom = 16.dp)
  ) {
    HorizontalPager(
      modifier = Modifier.weight(1f),
      state = pagerState,
      key = { pos -> OnboardingItemsData.list[pos] },
      pageSize = PageSize.Fill
    ) { pos ->
      DefaultOnboardingItem(
        item = OnboardingItemsData.list[pos],
        testTag = "onboarding_item_$pos"
      )
    }
    Spacer(
      modifier = Modifier
        .fillMaxWidth()
        .height(32.dp)
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
        .height(64.dp)
    )
    if (continueHomeButtonVisible) {
      OnboardingFinishedButton(onButtonClicked = onOnboardingFinished)
      Spacer(modifier = Modifier.height(20.dp))
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, locale = "es")
@PreviewLightDark
@Composable
private fun DefaultOnboardingScreenPreview() {
  CappajvTheme(dynamicColor = false) {
    DefaultOnboardingScreen(onOnboardingFinished = {})
  }
}
