/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.features.catalog.categories

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Coffee
import androidx.compose.material.icons.twotone.CoffeeMaker
import androidx.compose.material.icons.twotone.Cookie
import androidx.compose.ui.graphics.vector.ImageVector
import dev.marlonlom.cappajv.tv.R

/**
 * Category entries enum class.
 *
 * @author marlonlom
 *
 * @property icon Item icon as image vector.
 * @property text Item text as string resource.
 */
enum class CategoryEntries(val icon: ImageVector, @StringRes val text: Int) {

  /** Category entry enum: Hot drinks. */
  HOT(Icons.TwoTone.Coffee, R.string.text_category_hot),

  /** Category entry enum: Cold drinks. */
  COLD(Icons.TwoTone.CoffeeMaker, R.string.text_category_cold),

  /** Category entry enum: Pastry. */
  PASTRY(Icons.TwoTone.Cookie, R.string.text_category_pastry),
}
