/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.list.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.ui.layout.DevicePosture
import dev.marlonlom.cappajv.ui.main.CappajvAppState
import dev.marlonlom.cappajv.ui.navigation.NavigationType
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

/**
 * Catalog list banner slot composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param modifier Modifier for this composable.
 */
@ExperimentalFoundationApi
@Composable
fun CatalogListBanner(appState: CappajvAppState, modifier: Modifier = Modifier) {
  val bannerImagesList = listOf(
    painterResource(R.drawable.img_catalog_home_banner_01),
    painterResource(R.drawable.img_catalog_home_banner_02),
    painterResource(R.drawable.img_catalog_home_banner_03),
    painterResource(R.drawable.img_catalog_home_banner_04),
    painterResource(R.drawable.img_catalog_home_banner_05),
  )
  val pagerState = rememberPagerState { bannerImagesList.size }

  LaunchedEffect(Unit) {
    while (true) {
      yield()
      delay(5000)
      val currentPageIndex = pagerState.currentPage + 1 % pagerState.pageCount
      val nextPage = if (currentPageIndex >= pagerState.pageCount) 0 else currentPageIndex
      pagerState.animateScrollToPage(nextPage)
    }
  }

  val horizontalPagerHeight = when {
    appState.isLandscape.not()
      .and(appState.devicePosture is DevicePosture.Separating)
      .and(appState.navigationType == NavigationType.NAVIGATION_RAIL) -> 100.dp

    appState.isLandscape.and(appState.navigationType == NavigationType.NAVIGATION_RAIL) -> 100.dp
    appState.isLandscape -> 120.dp
    else -> 160.dp
  }

  Column {
    HorizontalPager(
      state = pagerState,
      contentPadding = PaddingValues(0.dp),
      modifier = modifier
        .height(horizontalPagerHeight)
        .fillMaxWidth(),
    ) { page ->
      BannerCard(
        pageIndex = page,
        pagerState = pagerState,
        bannerImage = bannerImagesList[page],
      )
    }
    HorizontalPagerIndicator(
      activeColor = MaterialTheme.colorScheme.primary,
      pagerState = pagerState,
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(10.dp),
    )
  }
}

/**
 * Banner card item content composable ui.
 *
 * @author marlonlom
 *
 * @param pageIndex Page index for displayed banner.
 * @param pagerState Pager ui state for displayed banner.
 * @param bannerImage Banner picture painter resource.
 */
@ExperimentalFoundationApi
@Composable
private fun BannerCard(pageIndex: Int, pagerState: PagerState, bannerImage: Painter) {
  Card(
    onClick = {},
    shape = MaterialTheme.shapes.large,
    modifier = Modifier.graphicsLayer {
      val pageOffset = pagerState.currentPage.minus(pageIndex).plus(pagerState.currentPageOffsetFraction)

      lerp(
        start = 0.85f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f),
      ).also { scale ->
        scaleX = scale
        scaleY = scale
      }

      alpha = lerp(
        start = 0.5f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f),
      )
    },
  ) {
    Image(
      painter = bannerImage,
      contentDescription = null,
      contentScale = ContentScale.FillBounds,
      modifier = Modifier.fillMaxSize(),
    )
  }
}

/**
 * Horizontal pager indicator composable ui.
 *
 * @author marlonlom
 *
 * @param activeColor Color that indicates the active displayed banner.
 * @param pagerState Pager ui state for displayed banner.
 * @param modifier Modifier for this composable.
 */
@ExperimentalFoundationApi
@Composable
fun HorizontalPagerIndicator(activeColor: Color, pagerState: PagerState, modifier: Modifier = Modifier) {
  Row(
    modifier
      .wrapContentHeight()
      .fillMaxWidth()
      .padding(bottom = 10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
  ) {
    repeat(pagerState.pageCount) { iteration ->
      val color = when (pagerState.currentPage) {
        iteration -> activeColor
        else -> activeColor.copy(alpha = 0.25f)
      }
      Box(
        modifier = modifier
          .padding(2.dp)
          .clip(CircleShape)
          .background(color)
          .size(8.dp),
      )
    }
  }
}
