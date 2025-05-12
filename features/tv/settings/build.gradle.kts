/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("cappajv.android.library.tv")
  id("cappajv.android.lib.compose.tv")
  id("cappajv.android.koin")
  id("cappajv.spotless")
}

android {
  namespace = "dev.marlonlom.cappajv.tv.settings"
}

dependencies {
  implementation(project(":features:core:preferences"))
  implementation(project(":features:tv:designsystem"))
}
