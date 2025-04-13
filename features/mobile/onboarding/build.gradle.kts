/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("cappajv.android.library")
  id("cappajv.android.lib.compose")
  id("cappajv.spotless")
}

android {
  namespace = "dev.marlonlom.cappajv.mobile.onboarding"
}

dependencies {
  implementation(project(":features:mobile:designsystem"))

  implementation(libs.coil.compose)
}
