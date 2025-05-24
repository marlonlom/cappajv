/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.ui.util

import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

/**
 * Utility object for opening URLs using Chrome Custom Tabs.
 *
 * This object simplifies the process of launching a web URL inside a Chrome Custom Tab,
 * offering a more integrated and branded user experience compared to launching an external browser.
 *
 * @author marlonlom
 */
object CustomTabsOpener {

  /**
   * Opens the specified URL in a Chrome Custom Tab.
   *
   * This method builds a [CustomTabsIntent] with default parameters, including showing the page title,
   * enabling instant apps, and applying a default color scheme. It then launches the URL using the provided context.
   *
   *
   * Example usage:
   * ```
   * CustomTabsOpener.openUrl(context, "https://example.com")
   * ```
   *
   * @param context the context used to start the custom tab activity. Typically an application or activity context.
   * @param url the web address to open in the custom tab.
   */
  fun openUrl(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
      .setShowTitle(true)
      .setInstantAppsEnabled(true)
      .setDefaultColorSchemeParams(
        CustomTabColorSchemeParams.Builder().build(),
      ).build()

    builder.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    builder.launchUrl(context, url.toUri())
  }
}
