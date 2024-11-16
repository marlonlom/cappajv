/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.cappajv.features.welcome.onboarding

import dev.marlonlom.cappajv.R

/**
 * Onboarding items data single object.
 *
 * @author marlonlom
 */
internal object OnboardingItemsData {

  /** Onboarding items list. */
  val list = listOf(
    Triple(
      R.drawable.img_onboarding_01,
      R.string.text_onboarding_title_01,
      R.string.text_onboarding_detail_01,
    ),
    Triple(
      R.drawable.img_onboarding_02,
      R.string.text_onboarding_title_02,
      R.string.text_onboarding_detail_02,
    ),
    Triple(
      R.drawable.img_onboarding_03,
      R.string.text_onboarding_title_03,
      R.string.text_onboarding_detail_03,
    ),
  )
}
