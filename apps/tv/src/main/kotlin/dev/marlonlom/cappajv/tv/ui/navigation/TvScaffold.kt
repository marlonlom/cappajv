/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.ui.navigation

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import dev.marlonlom.cappajv.tv.catalog.detail.CatalogDetailTvScreen
import dev.marlonlom.cappajv.tv.catalog.favorites.CatalogFavoritesTvScreen
import dev.marlonlom.cappajv.tv.catalog.home.CatalogHomeTvScreen
import dev.marlonlom.cappajv.tv.settings.SettingsTvScreen

/**
 * A composable scaffold layout for TV interfaces.
 *
 * @author marlonlom
 */
@Composable
fun TvScaffold() {
  val context = LocalContext.current
  val versionNumber = remember(context) { context.versionNumber }
  val (focusRequester, focusedTab) = remember { FocusRequester.createRefs() }
  LaunchedEffect(Unit) { focusRequester.requestFocus() }
  var selectedTabIndex by remember { mutableIntStateOf(0) }
  var areTabsEnabled by remember { mutableStateOf(true) }
  var selectedCatalogId by remember { mutableLongStateOf(0L) }

  if (selectedCatalogId != 0L) {
    CatalogDetailTvScreen(
      detailId = selectedCatalogId,
      onNavigationBack = {
        selectedCatalogId = 0L
      },
    )
  } else {
    Column(
      modifier = Modifier
        .focusRequester(focusRequester)
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(horizontal = 48.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      TvScaffoldTabs(
        tabIndex = selectedTabIndex,
        areTabsVisible = { areTabsEnabled },
        onTabChanged = {
          focusRequester.saveFocusedChild()
          selectedTabIndex = it
        },
        focusedTab = focusedTab,
      )

      AnimatedContent(
        targetState = selectedTabIndex,
        transitionSpec = {
          if (targetState > initialState) {
            slideInHorizontally { width -> width } + fadeIn() togetherWith
              slideOutHorizontally { width -> -width } + fadeOut()
          } else {
            slideInHorizontally { width -> -width } + fadeIn() togetherWith
              slideOutHorizontally { width -> width } + fadeOut()
          }
        },
      ) { tabIndex ->
        when (tabIndex) {
          TvDestinations.HOME.ordinal -> {
            CatalogHomeTvScreen(
              onItemClicked = { selectedCatalogId = it },
            )
          }

          TvDestinations.FAVORITES.ordinal -> {
            CatalogFavoritesTvScreen(
              onItemClicked = { selectedCatalogId = it },
              onUndoFavoriteDialogVisible = { enabled ->
                areTabsEnabled = enabled
              },
            )
          }

          TvDestinations.SETTINGS.ordinal -> {
            SettingsTvScreen(versionNumber)
          }
        }
      }
    }
  }
}

/** Retrieves the application's version name. */
private val Context.versionNumber: String
  get() {
    val packageName = packageName
    val metaData = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
    return metaData.versionName!!
  }
