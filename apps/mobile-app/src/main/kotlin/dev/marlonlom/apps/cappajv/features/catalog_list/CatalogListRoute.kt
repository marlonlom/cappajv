/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.catalog_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.apps.cappajv.R
import dev.marlonlom.apps.cappajv.core.database.entities.CatalogItemTuple
import dev.marlonlom.apps.cappajv.ui.main.AppContentCallbacks
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

/**
 * Catalog list route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param appContentCallbacks Application callbacks.
 * @param viewModel Catalog list viewmodel.
 */
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun CatalogListRoute(
  appState: CappajvAppState,
  appContentCallbacks: AppContentCallbacks,
  viewModel: CatalogListViewModel = koinViewModel(),
) {
  val coroutineScope = rememberCoroutineScope()
  val categoriesList = stringArrayResource(R.array.array_catalog_list_categories).toList()
  val firstCategory = categoriesList.first()
  val selectedCategory = rememberSaveable { mutableStateOf(firstCategory) }
  val catalogListUiState: CatalogListUiState by viewModel.uiState.collectAsStateWithLifecycle()
  val selectedCatalogId by viewModel.selectedCatalogId.collectAsStateWithLifecycle()
  val catalogListScrollState = rememberLazyListState()

  when (catalogListUiState) {
    CatalogListUiState.Empty -> Unit
    CatalogListUiState.Loading -> {
      Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        CircularProgressIndicator(
          modifier = Modifier.size(64.dp),
          strokeWidth = 2.dp
        )
      }
    }

    is CatalogListUiState.Listing -> {

      val originalCatalogList: List<CatalogItemTuple> = (catalogListUiState as CatalogListUiState.Listing).catalogMap
        .map { (_, tuples) -> tuples }.flatten()

      val filteredCatalogByCategory: (String) -> List<CatalogItemTuple> = { category ->
        when (category) {
          firstCategory -> originalCatalogList
          else -> originalCatalogList.filter { it.category == category }
        }
      }

      val catalogItemsList = rememberSaveable {
        mutableStateOf(filteredCatalogByCategory(firstCategory))
      }

      CatalogListContent(
        appState = appState,
        appContentCallbacks = appContentCallbacks,
        catalogItemsListState = catalogListScrollState,
        catalogItems = catalogItemsList.value,
        categories = categoriesList,
        selectedCategory = selectedCategory.value,
        selectedCatalogId = selectedCatalogId,
        onSelectedCategoryChanged = { category ->
          selectedCategory.value = category
          catalogItemsList.value = filteredCatalogByCategory(category)
          coroutineScope.launch {
            catalogListScrollState.animateScrollToItem(0)
          }
        },
        onCatalogItemSelected = { catalogId, isRouting ->
          viewModel.selectCatalogItem(catalogId)
          if (isRouting) {
            appState.goToDetail(catalogId)
          }
        },
      )
    }
  }
}
