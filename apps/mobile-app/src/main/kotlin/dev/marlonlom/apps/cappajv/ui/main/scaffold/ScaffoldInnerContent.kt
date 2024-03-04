/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main.scaffold

import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture

/**
 * Scaffold inner content type sealed interface definition.
 *
 * @author marlonlom
 *
 */
sealed interface ScaffoldInnerContentType {

  /**
   * Single pane scaffold inner content type data object.
   *
   * @author marlonlom
   *
   */
  data object SinglePane : ScaffoldInnerContentType

  /**
   * Two pane scaffold inner content type data object.
   *
   * @author marlonlom
   *
   * @property hingeRatio Hinge ratio as percentage number.
   */
  data class TwoPane(
    val hingeRatio: Float = 0.5f
  ) : ScaffoldInnerContentType
}

/**
 * Scaffold inner content classifier single object.
 *
 * @author marlonlom
 *
 */
object ScaffoldInnerContents {

  /**
   * Indicated scaffold inner content type by window size information and device posture.
   *
   * @param devicePosture
   *
   * @return Scaffold inner content type.
   */
  @JvmStatic
  fun indicateInnerContent(
    devicePosture: DevicePosture,
    isExpandedWidth: Boolean,
    isMediumWidth: Boolean,
    isCompactHeight: Boolean,
  ): ScaffoldInnerContentType = when {

    isExpandedWidth.and(isCompactHeight.not()) -> when (devicePosture) {
      DevicePosture.NormalPosture -> ScaffoldInnerContentType.TwoPane()
      is DevicePosture.SeparatingPosture.TableTopPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
      is DevicePosture.SeparatingPosture.BookPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
    }

    isMediumWidth.and(isCompactHeight.not()) -> when (devicePosture) {
      DevicePosture.NormalPosture -> ScaffoldInnerContentType.SinglePane
      is DevicePosture.SeparatingPosture.TableTopPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
      is DevicePosture.SeparatingPosture.BookPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
    }

    isCompactHeight.and(devicePosture is DevicePosture.NormalPosture) -> ScaffoldInnerContentType.SinglePane

    else -> ScaffoldInnerContentType.SinglePane
  }
}