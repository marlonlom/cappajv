/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.layout

import androidx.window.layout.FoldingFeature

/**
 * Device posture detector single object that uses [FoldingFeature] information.
 *
 * @author marlonlom
 */
object DevicePostureDetector {

  /**
   * Returns the device posture for selected layout information.
   *
   * @param foldingFeature Folding feature from application window.
   */
  @JvmStatic
  fun fromLayoutInfo(foldingFeature: FoldingFeature?): DevicePosture = when {

    foldingFeature != null && isBookMode(foldingFeature) -> {
      DevicePosture.Separating.Book(
        bounds = foldingFeature.bounds,
        hingeRatio = getHingeRatio(foldingFeature),
        orientation = foldingFeature.orientation,
        isSeparating = foldingFeature.isSeparating,
      )
    }

    foldingFeature != null && isTableTopMode(foldingFeature) -> {
      DevicePosture.Separating.TableTop(
        bounds = foldingFeature.bounds,
        hingeRatio = getHingeRatio(foldingFeature),
        orientation = foldingFeature.orientation,
        isSeparating = foldingFeature.isSeparating,
      )
    }

    else -> DevicePosture.Normal
  }

  private fun getHingeRatio(
    foldFeature: FoldingFeature
  ): Float = when (foldFeature.orientation) {
    FoldingFeature.Orientation.VERTICAL -> {
      val screenWidth = foldFeature.bounds.left + foldFeature.bounds.right
      foldFeature.bounds.left.toFloat() / screenWidth.toFloat()
    }

    else -> {
      val screenHeight = foldFeature.bounds.top + foldFeature.bounds.bottom
      foldFeature.bounds.top.toFloat() / screenHeight.toFloat()
    }
  }


  private fun isBookMode(foldingFeature: FoldingFeature) = foldingFeature.let {
    return@let when (it.state) {
      FoldingFeature.State.HALF_OPENED -> {
        it.state == FoldingFeature.State.HALF_OPENED
          && it.orientation == FoldingFeature.Orientation.VERTICAL
          && it.occlusionType == FoldingFeature.OcclusionType.NONE
      }

      FoldingFeature.State.FLAT -> {
        it.state == FoldingFeature.State.FLAT
          && it.orientation == FoldingFeature.Orientation.VERTICAL
          && it.occlusionType == FoldingFeature.OcclusionType.FULL
      }

      else -> false
    }
  }

  private fun isTableTopMode(foldingFeature: FoldingFeature) = foldingFeature.let {
    return@let when (it.state) {
      FoldingFeature.State.HALF_OPENED -> {
        it.state == FoldingFeature.State.HALF_OPENED
          && it.orientation == FoldingFeature.Orientation.HORIZONTAL
          && it.occlusionType == FoldingFeature.OcclusionType.NONE
      }

      FoldingFeature.State.FLAT -> {
        it.state == FoldingFeature.State.FLAT
          && it.orientation == FoldingFeature.Orientation.HORIZONTAL
          && it.occlusionType == FoldingFeature.OcclusionType.FULL
      }

      else -> false
    }
  }

}
