/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("cappajv.android.library.tv")
  id("cappajv.android.lib.compose.tv")
  id("cappajv.android.coil")
  id("cappajv.android.koin")
  id("cappajv.spotless")
}

android {
  namespace = "dev.marlonlom.cappajv.tv.catalog.favorites"
}

dependencies {
  implementation(project(":features:core:catalog"))
  implementation(project(":features:core:database"))
  implementation(project(":features:domain:catalog-favorites"))
  implementation(project(":features:tv:designsystem"))
}
