/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.home.slot

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.twotone.Coffee
import androidx.compose.material.icons.twotone.CoffeeMaker
import androidx.compose.material.icons.twotone.Cookie
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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
 * @param itemsCount The number of items contained in this category.
 * @param isExpanded Lambda that returns whether the category is currently expanded.
 * @param onTitleClicked Callback invoked when the title row is clicked.
 */
@Composable
internal fun CategoryTitleSlot(
  title: String,
  index: Int,
  itemsCount: Int,
  isExpanded: MutableState<Boolean>,
  onTitleClicked: () -> Unit,
) {
  val imageVector = when (index) {
    0 -> Icons.TwoTone.Coffee
    1 -> Icons.TwoTone.CoffeeMaker
    else -> Icons.TwoTone.Cookie
  }

  val rotationAngle by animateFloatAsState(
    targetValue = if (isExpanded.value) 180f else 0f,
    animationSpec = tween(durationMillis = 300),
  )

  Row(
    modifier = Modifier
      .testTag("category_row_$index")
      .fillMaxWidth()
      .combinedClickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = { onTitleClicked() },
      )
      .paddingFromBaseline(top = 30.dp, bottom = 10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    Icon(
      imageVector = imageVector,
      contentDescription = null,
      modifier = Modifier,
      tint = MaterialTheme.colorScheme.primary,
    )

    Text(
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.primary,
      text = "$title ($itemsCount)",
      style = MaterialTheme.typography.bodyLarge,
    )

    Spacer(Modifier.weight(1f))

    Icon(
      imageVector = Icons.Rounded.KeyboardArrowDown,
      contentDescription = if (isExpanded.value) "Collapsed" else "Expanded",
      modifier = Modifier.rotate(rotationAngle),
      tint = MaterialTheme.colorScheme.primary,
    )
  }
}
