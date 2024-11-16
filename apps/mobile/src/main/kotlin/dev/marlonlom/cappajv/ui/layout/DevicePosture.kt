/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.ui.layout

import android.graphics.Rect
import androidx.window.layout.FoldingFeature

/**
 * Information about the posture of the device.
 *
 * @author marlonlom
 */
sealed interface DevicePosture {

  /**
   * Normal posture of the device.
   *
   * @author marlonlom
   */
  data object Normal : DevicePosture

  /**
   * Separating posture of the half opened device.
   *
   * @author marlonlom
   *
   */
  sealed interface Separating : DevicePosture {
    /** Folding feature rectangle bounds. */
    val bounds: Rect

    /** Hinge ratio for screen separation. */
    val hingeRatio: Float

    /** Folding feature axis orientation indication. */
    val orientation: FoldingFeature.Orientation

    /** Indicates if folding feature is separating. */
    val isSeparating: Boolean

    /**
     * Book posture of the device.
     *
     * @author marlonlom
     *
     * @property bounds Folding feature rectangle bounds.
     * @property hingeRatio Hinge ratio for screen separation.
     * @property orientation Folding feature axis orientation indication.
     * @property isSeparating True/False indicating if folding feature is separating.
     */
    data class Book(
      override val bounds: Rect,
      override val hingeRatio: Float,
      override val orientation: FoldingFeature.Orientation,
      override val isSeparating: Boolean,
    ) : Separating

    /**
     * Table top posture of the device.
     *
     * @author marlonlom
     *
     * @property bounds Folding feature rectangle bounds.
     * @property hingeRatio Hinge ratio for screen separation.
     * @property orientation Folding feature axis orientation indication.
     * @property isSeparating True/False indicating if folding feature is separating.
     */
    data class TableTop(
      override val bounds: Rect,
      override val hingeRatio: Float,
      override val orientation: FoldingFeature.Orientation,
      override val isSeparating: Boolean,
    ) : Separating
  }
}
