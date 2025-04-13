/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.mobile.onboarding.component.OnboardingItem
import dev.marlonlom.cappajv.mobile.onboarding.model.DotIndicatorConfig
import dev.marlonlom.cappajv.mobile.onboarding.model.OnboardingItemsData
import dev.marlonlom.cappajv.mobile.onboarding.slot.FooterSlot

/**
 * Onboarding screen composable.
 *
 * @author marlonlom
 *
 * @param onOnboardingFinished Action for continue to home screen button clicked.
 */
@Composable
fun OnboardingScreen(onOnboardingFinished: () -> Unit) {
  val pagerState = rememberPagerState { OnboardingItemsData.list.size }
  var continueHomeButtonVisible by remember { mutableStateOf(false) }

  LaunchedEffect(pagerState) {
    snapshotFlow { pagerState.currentPage }.collect { page ->
      continueHomeButtonVisible = page == OnboardingItemsData.list.size - 1
    }
  }

  Column(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.surface)
      .safeContentPadding(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    HorizontalPager(
      modifier = Modifier
        .weight(1f)
        .widthIn(max = 480.dp),
      state = pagerState,
      key = { pos -> OnboardingItemsData.list[pos] },
      pageSize = PageSize.Fill,
    ) { pos ->
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
      ) {
        OnboardingItem(
          item = OnboardingItemsData.list[pos],
          testTag = "onboarding_item_$pos",
        )
      }
    }
    FooterSlot(
      config = DotIndicatorConfig(
        totalDots = OnboardingItemsData.list.size,
        selectedIndex = pagerState.currentPage,
        selectedColor = MaterialTheme.colorScheme.primary,
        unSelectedColor = MaterialTheme.colorScheme.inversePrimary,
      ),
      showOnboardedButton = continueHomeButtonVisible,
      onButtonClicked = onOnboardingFinished,
    )
  }
}
