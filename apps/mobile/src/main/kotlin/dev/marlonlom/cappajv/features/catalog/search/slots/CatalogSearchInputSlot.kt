/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.catalog.search.slots

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Clear
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.R
import dev.marlonlom.cappajv.ui.main.CappajvAppState

/**
 * Catalog search input slot composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param queryText Query text for searching.
 * @param showClearIcon True/False if query text should be cleared.
 * @param onSearchReady Action for query text ready for search.
 * @param modifier Modifier for this composable.
 */
@Composable
fun CatalogSearchInputSlot(
  appState: CappajvAppState,
  queryText: MutableState<String>,
  showClearIcon: State<Boolean>,
  onSearchReady: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val keyboardController = LocalSoftwareKeyboardController.current
  val textFieldColors = getSearchSlotTextFieldColors()
  val focusManager = LocalFocusManager.current

  OutlinedTextField(
    value = queryText.value,
    onValueChange = { queryText.value = it },
    modifier = modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.surface)
      .heightIn(min = 56.dp),
    singleLine = true,
    shape = MaterialTheme.shapes.medium,
    colors = textFieldColors,
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Text,
      imeAction = ImeAction.Search,
    ),
    keyboardActions = KeyboardActions(
      onSearch = {
        keyboardController?.hide()
        focusManager.clearFocus()
        onSearchReady()
      },
    ),
    leadingIcon = {
      Icon(
        imageVector = Icons.TwoTone.Search,
        contentDescription = "Search icon",
      )
    },
    trailingIcon = {
      if (showClearIcon.value) {
        IconButton(onClick = {
          queryText.value = ""
          focusManager.clearFocus()
          onSearchReady()
        }) {
          Icon(
            imageVector = Icons.TwoTone.Clear,
            contentDescription = "Clear icon",
          )
        }
      }
    },
    placeholder = {
      Text(stringResource(R.string.text_catalog_search_placeholder))
    },
  )
}

@Composable
private fun getSearchSlotTextFieldColors() = OutlinedTextFieldDefaults.colors(
  cursorColor = MaterialTheme.colorScheme.onSurface,
  errorBorderColor = MaterialTheme.colorScheme.onSurface,
  errorContainerColor = MaterialTheme.colorScheme.surface,
  errorCursorColor = MaterialTheme.colorScheme.error,
  errorLabelColor = MaterialTheme.colorScheme.onSurface,
  errorLeadingIconColor = MaterialTheme.colorScheme.onSurface,
  errorTextColor = MaterialTheme.colorScheme.onSurface,
  errorTrailingIconColor = MaterialTheme.colorScheme.onSurface,
  focusedBorderColor = MaterialTheme.colorScheme.primary,
  focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f),
  focusedLabelColor = MaterialTheme.colorScheme.primary,
  focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
  focusedTextColor = MaterialTheme.colorScheme.primary,
  focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
  unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
  unfocusedContainerColor = MaterialTheme.colorScheme.surface,
  unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
  unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
  unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
  unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
)
