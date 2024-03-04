/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.features.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.cappajv.features.welcome.parts.WelcomeButton
import dev.marlonlom.apps.cappajv.features.welcome.parts.WelcomeDetail
import dev.marlonlom.apps.cappajv.features.welcome.parts.WelcomePosterImage
import dev.marlonlom.apps.cappajv.features.welcome.parts.WelcomeTitle
import dev.marlonlom.apps.cappajv.features.welcome.screens.LandscapeCompactWelcomeScreen
import dev.marlonlom.apps.cappajv.features.welcome.screens.PortraitWelcomeScreen
import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.CappajvAppState
import dev.marlonlom.apps.cappajv.ui.main.scaffold.ScaffoldInnerContentType
import timber.log.Timber

/**
 * Welcome route composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
fun WelcomeRoute(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit
) {
  val contentHorizontalPadding = when {
    appState.devicePosture is DevicePosture.SeparatingPosture.BookPosture -> 0.dp
    appState.isCompactWidth.not() -> 80.dp
    else -> 20.dp
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = contentHorizontalPadding),
    contentAlignment = Alignment.Center
  ) {
    when (appState.scaffoldInnerContentType) {
      ScaffoldInnerContentType.SinglePane -> {
        when {
          appState.isCompactHeight -> {
            LandscapeCompactWelcomeScreen(appState, onContinueHomeButtonClicked)
          }

          else -> {
            PortraitWelcomeScreen(appState, onContinueHomeButtonClicked)
          }
        }
      }

      is ScaffoldInnerContentType.TwoPane -> {
        appState.devicePosture is DevicePosture.SeparatingPosture
        Timber.d("")
      }
    }
    /*when {
      appState.scaffoldInnerContentType.and(appState.is10InTabletWidth.not()) -> {
        FoldedPortraitWelcomeScreen(appState, onContinueHomeButtonClicked)
      }

      appState.isDeviceBookPostureHorizontal.and(appState.is10InTabletWidth.not()) -> {
        FoldedLandscapeWelcomeScreen(appState, onContinueHomeButtonClicked)
      }

      appState.isDeviceSeparatingHorizontal.and(appState.is10InTabletWidth.not()) -> {
        FoldingLandscapeWelcomeScreen(appState, onContinueHomeButtonClicked)
      }

      appState.isDeviceSeparatingVertical.and(appState.is10InTabletWidth.not()) -> {
        FoldingPortraitWelcomeScreen(appState, onContinueHomeButtonClicked)
      }

    }*/
  }
}

/**
 * Folded Landscape welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
fun FoldedLandscapeWelcomeScreen(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit
) {
  Column(modifier = Modifier.fillMaxSize()) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.5f)
        .padding(horizontal = 20.dp),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth(0.5f)
          .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        WelcomePosterImage()
      }
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        WelcomeTitle(appState)
        WelcomeDetail(appState)
        WelcomeButton(onContinueHomeButtonClicked)
      }
    }
    Row(
      modifier = Modifier
        .fillMaxSize(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {

    }
  }
}

/**
 * Folded Portrait welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
internal fun FoldedPortraitWelcomeScreen(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit,
) {
  Row(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxWidth(0.5f)
        .fillMaxHeight(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      WelcomePosterImage()
      WelcomeTitle(appState)
      WelcomeDetail(appState)
      WelcomeButton(onContinueHomeButtonClicked)
    }
    Column(
      modifier = Modifier
        .padding(top = 40.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {

    }
  }
}

/**
 * Folding Portrait welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
internal fun FoldingPortraitWelcomeScreen(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit,
) {
  Row(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxWidth(0.5f)
        .fillMaxHeight(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      WelcomePosterImage()
    }
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      WelcomeTitle(appState)
      WelcomeDetail(appState)
      WelcomeButton(onContinueHomeButtonClicked)
    }
  }
}

/**
 * Folding Landscape welcome screen composable ui.
 *
 * @author marlonlom
 *
 * @param appState Application ui state.
 * @param onContinueHomeButtonClicked Action for continue button clicked.
 */
@Composable
internal fun FoldingLandscapeWelcomeScreen(
  appState: CappajvAppState,
  onContinueHomeButtonClicked: () -> Unit,
) {
  Column(modifier = Modifier.fillMaxSize()) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.5f),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      WelcomePosterImage()
    }
    Row(
      modifier = Modifier
        .fillMaxSize(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        WelcomeTitle(appState)
        WelcomeDetail(appState)
        WelcomeButton(onContinueHomeButtonClicked)
      }
    }
  }
}
