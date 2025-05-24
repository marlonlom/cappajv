/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.mobile.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.marlonlom.cappajv.mobile.catalog.detail.CatalogDetailScreen
import dev.marlonlom.cappajv.mobile.catalog.favorites.CatalogFavoritesScreen
import dev.marlonlom.cappajv.mobile.catalog.home.CatalogHomeScreen
import dev.marlonlom.cappajv.mobile.settings.SettingsScreen
import dev.marlonlom.cappajv.mobile.ui.util.CatalogItemSharingUtil
import dev.marlonlom.cappajv.mobile.ui.util.CatalogListDetailUtil.detailContentPadding
import dev.marlonlom.cappajv.mobile.ui.util.CatalogListDetailUtil.gridColumnsCount
import dev.marlonlom.cappajv.mobile.ui.util.CustomTabsOpener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

/**
 * Displays the content area within the Cappajv scaffold layout.
 *
 * This composable manages the main UI based on the current navigation destination,
 * the layout type (e.g., single-pane, dual-pane, or three-pane), and a navigator
 * for handling pane-specific navigation.
 *
 * @author marlonlom
 *
 * @param currentDestination The currently selected destination in the app's navigation graph.
 * Used to determine which screen content to display.
 * @param layoutType The type of layout being used (e.g., single-pane, dual-pane, three-pane).
 * Influences how content is arranged on the screen.
 * @param navigator A navigator instance for managing navigation between panes in a multi-pane layout.
 * @param coroutineScope A [CoroutineScope] used for launching side effects or navigation events. Defaults to a remembered scope.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalCoroutinesApi::class)
@Composable
internal fun CappajvScaffoldContent(
  currentDestination: CappajvDestinations,
  layoutType: NavigationSuiteType,
  navigator: ThreePaneScaffoldNavigator<Long>,
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
  val context = LocalContext.current

  val onDetailSelected: (Long) -> Unit = { id ->
    coroutineScope.launch { navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, id) }
  }

  val isNavigationRailVisible: () -> Boolean = { layoutType == NavigationSuiteType.NavigationRail }

  val isTwoPaneVisible: () -> Boolean = {
    navigator.scaffoldValue.let {
      (it.secondary == PaneAdaptedValue.Expanded).and(it.primary == PaneAdaptedValue.Expanded)
    }
  }

  BackHandler(navigator.canNavigateBack()) {
    coroutineScope.launch {
      navigator.navigateBack()
    }
  }

  NavigableListDetailPaneScaffold(
    navigator = navigator,
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .safeContentPadding(),
    listPane = {
      AnimatedPane(
        modifier = Modifier.preferredWidth(280.dp),
        enterTransition = EnterTransition.None,
        exitTransition = ExitTransition.None,
      ) {
        when (currentDestination) {
          CappajvDestinations.HOME -> {
            CatalogHomeScreen(
              columnsCount = gridColumnsCount(
                isTwoPaneVisible = isTwoPaneVisible,
                isNavigationRailVisible = isNavigationRailVisible,
              ),
              onCatalogGridItemClicked = { onDetailSelected(it) },
            )
          }

          CappajvDestinations.FAVORITES -> {
            CatalogFavoritesScreen(
              columnsCount = gridColumnsCount(
                isTwoPaneVisible = isTwoPaneVisible,
                isNavigationRailVisible = isNavigationRailVisible,
              ),
              onCatalogGridItemClicked = { onDetailSelected(it) },
            )
          }

          CappajvDestinations.SETTINGS -> {
            SettingsScreen(
              openExternalUrl = { url ->
                CustomTabsOpener.openUrl(context, url)
              },
            )
          }
        }
      }
    },
    detailPane = {
      val catalogId = navigator.currentDestination?.contentKey
      val detailsPadding = when {
        isTwoPaneVisible() ->
          Modifier
            .padding(start = 20.dp)
            .background(
              color = MaterialTheme.colorScheme.surfaceVariant,
              shape = MaterialTheme.shapes.large,
            )
            .padding(start = 40.dp, end = 20.dp, top = 20.dp)

        else -> Modifier
      }
      val detailsAlign = when {
        catalogId == null -> Alignment.Center
        else -> Alignment.TopCenter
      }

      Box(
        modifier = detailsPadding,
        contentAlignment = detailsAlign,
        content = {
          CatalogDetailScreen(
            catalogId = catalogId ?: 0L,
            showBackButton = { !isTwoPaneVisible() },
            onNavigateBack = {
              if (navigator.canNavigateBack()) {
                coroutineScope.launch {
                  navigator.navigateBack()
                }
              }
            },
            onShopLinkUrlClicked = { url ->
              CustomTabsOpener.openUrl(context, url)
            },
            onShareMessageSent = { messageText ->
              CatalogItemSharingUtil.beginShare(context, messageText)
            },
            contentPadding = detailContentPadding(
              isTwoPaneVisible = isTwoPaneVisible,
              isNavigationRailVisible = isNavigationRailVisible,
            ),
          )
        },
      )
    },
  )
}
