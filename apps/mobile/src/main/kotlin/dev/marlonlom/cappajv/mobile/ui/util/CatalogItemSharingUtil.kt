/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.util

import android.content.Context
import android.content.Intent
import dev.marlonlom.cappajv.R

/**
 * Utility object for sharing catalog item information with other applications.
 *
 * This object provides a method to initiate a sharing intent, allowing the user
 * to share a message (typically a catalog item's details) via available apps
 * that accept plain text input (e.g., messaging, email, or social media apps).
 *
 * @author marlonlom
 */
object CatalogItemSharingUtil {

  /**
   * Launches a share intent with the provided message.
   *
   * This method uses [Intent.ACTION_SEND] to create a plain text sharing intent
   * and starts an activity chooser to let the user pick their preferred app for sharing.
   *
   * Example usage:
   * ```
   * CatalogItemSharingUtil.beginShare(context, "Check out this catalog item!")
   * ```
   *
   * @param context the context used to start the sharing activity.
   * @param message the message content to be shared with other apps.
   *
   */
  fun beginShare(context: Context, message: String) {
    Intent(Intent.ACTION_SEND).apply {
      type = "text/plain"
      putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
      putExtra(Intent.EXTRA_TEXT, message)
    }.apply {
      context.startActivity(
        /* intent = */
        Intent.createChooser(this, context.getString(R.string.text_catalog_detail_button_share)),
        /* options = */
        null,
      )
    }
  }
}
