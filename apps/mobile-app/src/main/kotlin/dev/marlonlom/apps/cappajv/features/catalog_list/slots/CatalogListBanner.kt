/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list.slots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import dev.marlonlom.apps.cappajv.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

/**
 * Catalog list banner slot composable ui.
 *
 * @author marlonlom
 *
 * @param modifier
 */
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun CatalogListBanner(
  modifier: Modifier = Modifier
) {
  val pagerState = rememberPagerState(initialPage = 0)
  val bannerImagesList = listOf(
    painterResource(R.drawable.img_catalog_home_banner_01),
    painterResource(R.drawable.img_catalog_home_banner_02),
    painterResource(R.drawable.img_catalog_home_banner_03),
    painterResource(R.drawable.img_catalog_home_banner_04),
    painterResource(R.drawable.img_catalog_home_banner_05),
  )

  LaunchedEffect(Unit) {
    while (true) {
      yield()
      delay(5000)
      pagerState.animateScrollToPage(
        page = (pagerState.currentPage + 1) % (pagerState.pageCount)
      )
    }
  }

  Column {
    HorizontalPager(
      count = bannerImagesList.size,
      state = pagerState,
      contentPadding = PaddingValues(0.dp),
      modifier = modifier
        .height(160.dp)
        .fillMaxWidth()
    ) { page ->
      Card(onClick = {}, shape = MaterialTheme.shapes.large, modifier = Modifier.graphicsLayer {
        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

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
      }) {
        Image(
          painter = bannerImagesList[page],
          contentDescription = null,
          contentScale = ContentScale.FillBounds,
          modifier = Modifier.fillMaxSize()
        )
      }
    }
    HorizontalPagerIndicator(
      pagerState = pagerState,
      activeColor = MaterialTheme.colorScheme.primary,
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(10.dp),
    )
  }

}
