/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail.slot

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material.icons.twotone.Shop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.mobile.catalog.detail.R
import dev.marlonlom.cappajv.mobile.catalog.detail.component.CatalogDetailActionButton

/**
 * A composable function that displays a slot for action buttons such as Like, Shop, and Share.
 *
 * @author marlonlom
 *
 * @param likeButtonIcon The icon to display on the like button. It should be an instance of [ImageVector].
 * @param onLikeButtonClicked Lambda function invoked when the Like button is clicked.
 * @param onShopButtonClicked Lambda function invoked when the Shop button is clicked.
 * @param onShareButtonClicked Lambda function invoked when the Share button is clicked.
 * @param scrollState The [ScrollState] controlling the scroll behavior of the content.
 * @param contentPadding Padding values to apply around the content of the screen.
 */
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
internal fun CatalogDetailActionButtonsSlot(
  likeButtonIcon: ImageVector,
  onLikeButtonClicked: () -> Unit,
  onShopButtonClicked: () -> Unit,
  onShareButtonClicked: () -> Unit,
  scrollState: ScrollState = rememberScrollState(),
  contentPadding: PaddingValues = PaddingValues(0.dp),
) = Row(
  modifier = Modifier
    .fillMaxWidth()
    .padding(contentPadding)
    .horizontalScroll(scrollState),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.spacedBy(8.dp),
) {
  CatalogDetailActionButton(
    buttonText = R.string.text_like,
    buttonIcon = likeButtonIcon,
    buttonTestTag = "detail_like_btn",
    onButtonClicked = { onLikeButtonClicked() },
  )
  CatalogDetailActionButton(
    buttonText = R.string.text_shop,
    buttonIcon = Icons.TwoTone.Shop,
    buttonTestTag = "detail_shop_btn",
    onButtonClicked = { onShopButtonClicked() },
  )
  CatalogDetailActionButton(
    buttonText = R.string.text_share,
    buttonIcon = Icons.TwoTone.Share,
    buttonTestTag = "detail_share_btn",
    onButtonClicked = { onShareButtonClicked() },
  )
}
