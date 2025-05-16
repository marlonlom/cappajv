/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.tv.designsystem.component

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.max
import dev.marlonlom.cappajv.tv.designsystem.component.CircularProgressIndicatorConfig as C

/**
 * An infinitely animating circular progress indicator.
 *
 * @author marlonlom
 *
 * @param color The color of the indicator.
 * @param strokeWidth The stroke width of the indicator.
 * @param trackColor The color of the background track.
 * @param strokeCap The stroke cap of the indicator.
 */
@Composable
fun CircularProgressIndicator(
  color: Color = MaterialTheme.colorScheme.primary,
  strokeWidth: Dp = 4.dp,
  trackColor: Color = MaterialTheme.colorScheme.surface,
  strokeCap: StrokeCap = StrokeCap.Round,
) {
  val transition = rememberInfiniteTransition("loading")

  val stroke = with(LocalDensity.current) {
    Stroke(width = strokeWidth.toPx(), cap = strokeCap)
  }

  val currentRotation = transition.animateValue(
    0,
    C.ROTATIONS_PER_CYCLE,
    Int.VectorConverter,
    infiniteRepeatable(
      animation = tween(
        durationMillis = C.ROTATION_DURATION * C.ROTATIONS_PER_CYCLE,
        easing = LinearEasing,
      ),
    ),
    "loading_current_rotation",
  )
  val baseRotation = transition.animateFloat(
    0f,
    C.BASE_ROTATION_ANGLE,
    infiniteRepeatable(
      animation = tween(
        durationMillis = C.ROTATION_DURATION,
        easing = LinearEasing,
      ),
    ),
    "loading_base_rotation_angle",
  )
  val endAngle = transition.animateFloat(
    0f,
    C.JUMP_ROTATION_ANGLE,
    infiniteRepeatable(
      animation = keyframes {
        durationMillis = C.HEAD_AND_TAIL_ANIMATION_DURATION + C.HEAD_AND_TAIL_DELAY_DURATION
        0f at 0 using C.circularEasing
        C.JUMP_ROTATION_ANGLE at C.HEAD_AND_TAIL_ANIMATION_DURATION
      },
    ),
    "loading_end_rotation_angle",
  )
  val startAngle = transition.animateFloat(
    0f,
    C.JUMP_ROTATION_ANGLE,
    infiniteRepeatable(
      animation = keyframes {
        durationMillis = C.HEAD_AND_TAIL_ANIMATION_DURATION + C.HEAD_AND_TAIL_DELAY_DURATION
        0f at C.HEAD_AND_TAIL_DELAY_DURATION using C.circularEasing
        C.JUMP_ROTATION_ANGLE at durationMillis
      },
    ),
    "loading_start_angle",
  )

  Canvas(
    modifier = Modifier
      .progressSemantics()
      .size(C.circularIndicatorDiameter),
  ) {
    drawCircularIndicatorTrack(trackColor, stroke)
    val currentRotationAngleOffset = (currentRotation.value * C.ROTATION_ANGLE_OFFSET) % 360f
    val sweep = abs(endAngle.value - startAngle.value)
    val offset = C.START_ANGLE_OFFSET + currentRotationAngleOffset + baseRotation.value
    drawIndeterminateCircularIndicator(
      startAngle.value + offset,
      strokeWidth,
      sweep,
      color,
      stroke,
    )
  }
}

/**
 * Draws a circular arc within the current DrawScope.
 *
 * @author marlonlom
 *
 * @param startAngle The starting angle of the arc in degrees.
 * @param sweep The sweep angle of the arc in degrees.
 * @param color The color to paint the arc.
 * @param stroke The stroke configuration for the arc.
 */
private fun DrawScope.drawCircularIndicator(startAngle: Float, sweep: Float, color: Color, stroke: Stroke) {
  val diameterOffset = stroke.width / 2
  val arcDimen = size.width - 2 * diameterOffset
  drawArc(
    color = color,
    startAngle = startAngle,
    sweepAngle = sweep,
    useCenter = false,
    topLeft = Offset(diameterOffset, diameterOffset),
    size = Size(arcDimen, arcDimen),
    style = stroke,
  )
}

/**
 * Draws the track (full circle) for the circular indicator.
 *
 * @author marlonlom
 *
 * @param color The color of the track.
 * @param stroke The stroke configuration for the track.
 */
private fun DrawScope.drawCircularIndicatorTrack(color: Color, stroke: Stroke) =
  drawCircularIndicator(0f, 360f, color, stroke)

/**
 * Draws an indeterminate circular indicator arc with a specific starting angle and sweep.
 *
 * @author marlonlom
 *
 * @param startAngle The starting angle of the arc in degrees.
 * @param strokeWidth The width of the indicator stroke.
 * @param sweep The sweep angle of the arc in degrees.
 * @param color The color to paint the indicator.
 * @param stroke The stroke configuration for the indicator.
 */
private fun DrawScope.drawIndeterminateCircularIndicator(
  startAngle: Float,
  strokeWidth: Dp,
  sweep: Float,
  color: Color,
  stroke: Stroke,
) {
  val strokeCapOffset = if (stroke.cap == StrokeCap.Butt) {
    0f
  } else {
    (180.0 / PI).toFloat() * (strokeWidth / (C.circularIndicatorDiameter / 2)) / 2f
  }
  val adjustedStartAngle = startAngle + strokeCapOffset
  val adjustedSweep = max(sweep, 0.1f)
  drawCircularIndicator(adjustedStartAngle, adjustedSweep, color, stroke)
}

/**
 * Configuration for the circular progress indicator.
 *
 * @author marlonlom
 */
private object CircularProgressIndicatorConfig {
  /** Number of full rotations in a complete cycle. */
  const val ROTATIONS_PER_CYCLE = 5

  /** Duration of one full rotation cycle in milliseconds. */
  const val ROTATION_DURATION = 1332

  /** Base starting angle for the rotation animation. */
  const val BASE_ROTATION_ANGLE = 286f

  /** Angle increment applied during the rotation jump. */
  const val JUMP_ROTATION_ANGLE = 290f

  /** Duration of the head and tail animation in milliseconds. */
  const val HEAD_AND_TAIL_ANIMATION_DURATION = (ROTATION_DURATION * 0.5).toInt()

  /** Delay before the head and tail animation starts in milliseconds. */
  const val HEAD_AND_TAIL_DELAY_DURATION = HEAD_AND_TAIL_ANIMATION_DURATION

  /** Starting angle offset in degrees. */
  const val START_ANGLE_OFFSET = -90f

  /** Total rotation angle offset in degrees. */
  const val ROTATION_ANGLE_OFFSET = (BASE_ROTATION_ANGLE + JUMP_ROTATION_ANGLE) % 360f

  /** Easing function for the circular animation. */
  val circularEasing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)

  /** Diameter of the circular indicator. */
  val circularIndicatorDiameter = 38.dp
}
