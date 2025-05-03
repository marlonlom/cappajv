/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.catalog.favorites.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.DialogProperties
import dev.marlonlom.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.cappajv.mobile.catalog.favorites.R

/**
 * Displays an alert dialog to confirm the removal of a catalog item from the favorites list.
 *
 * @author marlonlom
 *
 * @param shouldShowDialog A mutable state that controls the visibility of the dialog.
 * When set to `true`, the dialog is shown. Setting it to `false` hides it.
 * @param catalogItem The [CatalogItemTuple] representing the item to be removed from favorites.
 * @param onConfirm Callback invoked when the user confirms the favorite removal action.
 * @param onDismiss Callback invoked when the dialog is dismissed without confirming.
 */
@Composable
internal fun UnFavoriteCatalogItemAlert(
  shouldShowDialog: MutableState<Boolean>,
  catalogItem: CatalogItemTuple,
  onConfirm: () -> Unit,
  onDismiss: () -> Unit,
) = AlertDialog(
  onDismissRequest = { },
  properties = DialogProperties(
    dismissOnBackPress = false,
    dismissOnClickOutside = false,
    usePlatformDefaultWidth = true,
  ),
  title = {
    Text(
      text = stringResource(R.string.text_undo_favorite_title),
      fontWeight = FontWeight.Bold,
    )
  },
  text = {
    Text(text = stringResource(R.string.text_undo_favorite_detail, catalogItem.title))
  },
  confirmButton = {
    Button(
      onClick = {
        shouldShowDialog.value = false
        onConfirm()
      },
      colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
      ),
      content = {
        Text(
          text = stringResource(R.string.text_undo_favorite_confirm),
          fontWeight = FontWeight.Bold,
        )
      },
    )
  },
  dismissButton = {
    TextButton(
      onClick = {
        shouldShowDialog.value = false
        onDismiss()
      },
      content = {
        Text(
          text = stringResource(R.string.text_undo_favorite_dismiss),
        )
      },
    )
  },
)
