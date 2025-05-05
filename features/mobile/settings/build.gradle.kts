/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("cappajv.android.library")
  id("cappajv.android.lib.compose")
  id("cappajv.android.coil")
  id("cappajv.android.koin")
  id("cappajv.spotless")
}

android {
  namespace = "dev.marlonlom.cappajv.mobile.settings"
}

dependencies {
  implementation(project(":features:core:preferences"))
  implementation(project(":features:mobile:designsystem"))
}
