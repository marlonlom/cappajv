/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.cappajv.domain.catalog.detail.CatalogDetailUiState
import dev.marlonlom.cappajv.mobile.catalog.detail.component.CatalogDetailDescriptionText
import dev.marlonlom.cappajv.mobile.catalog.detail.domain.CatalogDetailViewModel
import dev.marlonlom.cappajv.mobile.catalog.detail.slot.CatalogDetailActionButtonsSlot
import dev.marlonlom.cappajv.mobile.catalog.detail.slot.CatalogDetailHeadlineSlot
import dev.marlonlom.cappajv.mobile.catalog.detail.slot.CatalogDetailPunctuationsSlot
import dev.marlonlom.cappajv.mobile.catalog.detail.slot.CatalogDetailTopNavigationSlot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.koinViewModel

/**
 * Composable function that displays the details of a specific catalog item.
 *
 * @author marlonlom
 *
 * @param catalogId The unique identifier of the catalog item to display.
 * @param showBackButton A lambda function that determines whether to show the back button.
 * It should return `true` if the back button should be visible, and `false` otherwise.
 * @param onNavigateBack A callback function invoked when the user clicks the back button.
 * @param onShopLinkUrlClicked A callback function invoked when a shop link URL is clicked.
 * It receives the clicked URL as a parameter.
 * @param onShareMessageSent A callback function invoked after a share message has been sent.
 * It receives the shared message content as a parameter.
 * @param contentPadding Padding values to apply around the content of the screen.
 * Defaults to no padding.
 * @param viewModel The [CatalogDetailViewModel] used to manage the data and business logic
 * for this screen.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CatalogDetailScreen(
  catalogId: Long,
  showBackButton: () -> Boolean,
  onNavigateBack: () -> Unit,
  onShopLinkUrlClicked: (String) -> Unit,
  onShareMessageSent: (String) -> Unit,
  contentPadding: PaddingValues = PaddingValues(0.dp),
  viewModel: CatalogDetailViewModel = koinViewModel(),
) {
  val resources = LocalResources.current

  viewModel.find(catalogId)
  val uiState by viewModel.detail.collectAsStateWithLifecycle()

  LazyColumn {
    when (uiState) {
      is CatalogDetailUiState.Found -> {
        val item = (uiState as CatalogDetailUiState.Found).detail

        val showBackBtn = showBackButton()
        if (showBackBtn) {
          stickyHeader {
            CatalogDetailTopNavigationSlot(
              showBackButton = showBackButton,
              onNavigateBack = onNavigateBack,
            )
          }
        }

        item {
          CatalogDetailHeadlineSlot(
            detail = item.product,
            contentPadding = contentPadding,
          )
        }

        item {
          CatalogDetailActionButtonsSlot(
            likeButtonIcon = when {
              item.isFavorite -> Icons.Rounded.Favorite
              else -> Icons.Rounded.FavoriteBorder
            },
            onLikeButtonClicked = {
              viewModel.toggleFavorite(item.product, !item.isFavorite)
            },
            onShopButtonClicked = {
              onShopLinkUrlClicked(resources.getString(R.string.text_store_url))
            },
            onShareButtonClicked = {
              onShareMessageSent(resources.getString(R.string.text_detail_sharing, item.product.title))
            },
            contentPadding = contentPadding,
          )
        }

        item {
          CatalogDetailDescriptionText(
            item.product,
            contentPadding = contentPadding,
          )
        }

        item {
          CatalogDetailPunctuationsSlot(
            punctuations = item.points,
            contentPadding = contentPadding,
          )
        }
      }

      CatalogDetailUiState.NotFound -> {
        stickyHeader {
          Text(
            text = "Select a catalog item from the list.",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
          )
        }
      }

      CatalogDetailUiState.Loading -> {
        item(
          content = {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp),
              contentAlignment = Alignment.Center,
            ) {
              CircularProgressIndicator()
            }
          },
        )
      }
    }
  }
}
