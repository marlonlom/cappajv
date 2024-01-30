/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetail
import dev.marlonlom.apps.cappajv.features.catalog_detail.CatalogDetailViewModel
import dev.marlonlom.apps.cappajv.ui.util.CustomTabsOpener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

/**
 * App content callbacks data class.
 *
 * @author marlonlom
 *
 * @property openOssLicencesInfo Action for opening oss licences information window.
 * @property openExternalUrl Action for opening oss licences information window.
 * @property onProductSelectedForDetail Action for selecting book for details.
 * @property onFavoriteProductIconClicked Action for favorite book button clicked.
 * @property onRemoveFavoriteIconClicked
 */
data class AppContentCallbacks(
  val onOnboardingCompleter: () -> Unit,
  val openOssLicencesInfo: () -> Unit,
  val openExternalUrl: (String) -> Unit,
  val onProductSelectedForDetail: (String) -> Unit,
  val onFavoriteProductIconClicked: (CatalogDetail, Boolean) -> Unit,
  val onRemoveFavoriteIconClicked: (String) -> Unit
)

/**
 * Creates new app content callbacks object.
 *
 * @author marlonlom
 *
 * @param activityContext Activity context.
 * @param catalogDetailViewModel Catalog details viewmodel.
 */
@ExperimentalCoroutinesApi
@Composable
internal fun newAppContentCallbacks(
  activityContext: Context,
  catalogDetailViewModel: CatalogDetailViewModel
) = AppContentCallbacks(
  onOnboardingCompleter = {

  },
  openOssLicencesInfo = {
    Timber.d("[AppContent.openOssLicencesInfo] Should open oss licences information content.")
    activityContext.startActivity(Intent(activityContext, OssLicensesMenuActivity::class.java))
  },
  openExternalUrl = { externalUrl ->
    Timber.d("[AppContent.openExternalUrl] Should open external url '$externalUrl'.")
    CustomTabsOpener.openUrl(activityContext, externalUrl)
  },
  onProductSelectedForDetail = { productId ->
    Timber.d("[AppContent.onProductSelectedForDetail] Should select product[$productId] for details.")
    //bookDetailsViewModel.setSelectedBook(productId = productId)
  },
  onFavoriteProductIconClicked = { catalogDetail: CatalogDetail, markedFavorite: Boolean ->
    Timber.d("[AppContent.onFavoriteProductIconClicked] Should toggle Book[${catalogDetail.product.id}] as favorite? $markedFavorite.")
    //bookDetailsViewModel.toggleFavorite(catalogDetail, markedFavorite)
  },
) { bookId ->
  Timber.d("[AppContent.onRemoveFavoriteIconClicked] Should remove Book[$bookId] as favorite.")
  //favoriteBooksViewModel.removeFavorite(bookId)
}
