/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.ui.main

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dev.marlonlom.cappajv.ui.util.CatalogItemSharingUtil
import dev.marlonlom.cappajv.ui.util.CustomTabsOpener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

/**
 * App content callbacks data class.
 *
 * @author marlonlom
 *
 * @property onOnboardingComplete Action for onboarding completed.
 * @property openOssLicencesInfo Action for opening oss licences information window.
 * @property openExternalUrl Action for external url opening.
 * @property onShareIconClicked Action for performing sharing functionality.
 */
data class AppContentCallbacks(
  val onOnboardingComplete: () -> Unit,
  val openOssLicencesInfo: () -> Unit,
  val openExternalUrl: (String) -> Unit,
  val onShareIconClicked: (Context, String) -> Unit,
)

/**
 * Creates new app content callbacks object.
 *
 * @author marlonlom
 *
 * @param activityContext Activity context.
 */
@ExperimentalCoroutinesApi
@Composable
internal fun newAppContentCallbacks(
  activityContext: Context,
) = AppContentCallbacks(
  onOnboardingComplete = {

  },
  openOssLicencesInfo = {
    Timber.d("[AppContentCallbacks.openOssLicencesInfo] Should open oss licences information content.")
    activityContext.startActivity(Intent(activityContext, OssLicensesMenuActivity::class.java))
  },
  openExternalUrl = { externalUrl ->
    Timber.d("[AppContentCallbacks.openExternalUrl] Should open external url '$externalUrl'.")
    CustomTabsOpener.openUrl(activityContext, externalUrl)
  },
  onShareIconClicked = { ctx, message ->
    Timber.d("[AppContentCallbacks.onShareIconClicked] Should share a catalog item.")
    CatalogItemSharingUtil.beginShare(ctx, message)
  },
)
