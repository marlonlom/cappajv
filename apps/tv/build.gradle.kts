/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("cappajv.android.application.tv")
  id("cappajv.android.app.compose.tv")
  id("cappajv.android.coil")
  id("cappajv.android.koin")
  id("cappajv.spotless")
  id("kotlinx-serialization")
}

dependencies {

  implementation(project(":features:core:catalog"))
  implementation(project(":features:core:database"))
  implementation(project(":features:core:preferences"))

  implementation(project(":features:tv:catalog-detail"))
  implementation(project(":features:tv:catalog-home"))
  implementation(project(":features:tv:catalog-favorites"))
  implementation(project(":features:tv:designsystem"))
  implementation(project(":features:tv:onboarding"))
  implementation(project(":features:tv:settings"))

  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.bundles.database.room)
  implementation(libs.jakewharton.timber)

  implementation(libs.kotlinx.serialization.json)
}
