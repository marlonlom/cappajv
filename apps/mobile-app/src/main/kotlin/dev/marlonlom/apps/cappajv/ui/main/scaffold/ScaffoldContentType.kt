/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.cappajv.ui.main.scaffold

import dev.marlonlom.apps.cappajv.ui.layout.DevicePosture
import dev.marlonlom.apps.cappajv.ui.main.scaffold.ScaffoldContentType.SinglePane
import dev.marlonlom.apps.cappajv.ui.main.scaffold.ScaffoldContentType.TwoPane

/**
 * Scaffold inner content type sealed interface definition.
 *
 * @author marlonlom
 *
 */
sealed interface ScaffoldContentType {

  /**
   * Single pane scaffold inner content type data object.
   *
   * @author marlonlom
   *
   */
  data object SinglePane : ScaffoldContentType

  /**
   * Two pane scaffold inner content type data object.
   *
   * @author marlonlom
   *
   * @property hingeRatio Hinge ratio as percentage number.
   */
  data class TwoPane(
    val hingeRatio: Float = 0.5f
  ) : ScaffoldContentType
}

/**
 * Scaffold inner content classifier single object.
 *
 * @author marlonlom
 *
 */
object ScaffoldContentClassifier {

  /**
   * Indicated scaffold inner content type by window size information and device posture.
   *
   * @param devicePosture
   *
   * @return Scaffold inner content type.
   */
  @JvmStatic
  fun classify(
    devicePosture: DevicePosture,
    isExpandedWidth: Boolean,
    isMediumWidth: Boolean,
    isCompactHeight: Boolean,
  ): ScaffoldContentType = when {

    isMediumWidth.not().and(!isExpandedWidth.not()) -> when (devicePosture) {
      is DevicePosture.Separating.TableTop -> TwoPane(devicePosture.hingeRatio)
      else -> SinglePane
    }

    isExpandedWidth.and(isCompactHeight.not()) -> when (devicePosture) {
      DevicePosture.Normal -> TwoPane()
      is DevicePosture.Separating.TableTop -> TwoPane(devicePosture.hingeRatio)
      is DevicePosture.Separating.Book -> TwoPane(devicePosture.hingeRatio)
    }

    isMediumWidth.and(isCompactHeight.not()) -> when (devicePosture) {
      DevicePosture.Normal -> SinglePane
      is DevicePosture.Separating.TableTop -> TwoPane(devicePosture.hingeRatio)
      is DevicePosture.Separating.Book -> TwoPane(devicePosture.hingeRatio)
    }

    isCompactHeight.and(devicePosture is DevicePosture.Normal) -> SinglePane

    else -> SinglePane
  }
}
