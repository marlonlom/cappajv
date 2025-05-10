/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.slot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Coffee
import androidx.compose.material.icons.twotone.CoffeeMaker
import androidx.compose.material.icons.twotone.Cookie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

/**
 * Displays a category title section with an item count.
 *
 * This composable is typically used to label a section in a catalog, such as a type of coffee,
 * along with the number of items in that category.
 *
 * @author marlonlom
 *
 * @param title The title of the category to be displayed.
 * @param index The index of the category in the list.
 */
@Composable
fun CategoryTitleSlot(title: String, index: Int) {
  val imageVector = when (index) {
    0 -> Icons.TwoTone.Coffee
    1 -> Icons.TwoTone.CoffeeMaker
    else -> Icons.TwoTone.Cookie
  }

  Row(
    modifier = Modifier
      .testTag("category_row_$index")
      .fillMaxWidth()
      .padding(top = 30.dp, bottom = 10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    Icon(
      imageVector = imageVector,
      contentDescription = null,
      modifier = Modifier.size(ButtonDefaults.IconSize),
      tint = MaterialTheme.colorScheme.secondary,
    )

    Text(
      text = title,
      color = MaterialTheme.colorScheme.secondary,
      style = MaterialTheme.typography.bodyMedium,
    )
  }
}
