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

    (isMediumWidth.or(isExpandedWidth)).not() -> when (devicePosture) {
      is DevicePosture.Separating.TableTop -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
      else -> ScaffoldInnerContentType.SinglePane
    }

    isExpandedWidth.and(isCompactHeight.not()) -> when (devicePosture) {
      DevicePosture.Normal -> ScaffoldInnerContentType.TwoPane()
      is DevicePosture.Separating.TableTop -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
      is DevicePosture.Separating.Book -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
    }

    isMediumWidth.and(isCompactHeight.not()) -> when (devicePosture) {
      DevicePosture.Normal -> ScaffoldInnerContentType.SinglePane
      is DevicePosture.Separating.TableTop -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
      is DevicePosture.Separating.Book -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
    }

    isCompactHeight.and(devicePosture is DevicePosture.Normal) -> ScaffoldInnerContentType.SinglePane

    else -> ScaffoldInnerContentType.SinglePane
  }
}
