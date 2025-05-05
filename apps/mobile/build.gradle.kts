/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
  id("cappajv.android.application.mobile")
  id("cappajv.android.app.compose.mobile")
  id("cappajv.android.coil")
  id("cappajv.android.koin")
  id("cappajv.spotless")
  id("com.google.android.gms.oss-licenses-plugin")
}

dependencies {

  implementation(project(":features:core:catalog"))
  implementation(project(":features:core:database"))
  implementation(project(":features:core:preferences"))

  implementation(project(":features:mobile:catalog-detail"))
  implementation(project(":features:mobile:catalog-favorites"))
  implementation(project(":features:mobile:catalog-home"))
  implementation(project(":features:mobile:designsystem"))
  implementation(project(":features:mobile:onboarding"))
  implementation(project(":features:mobile:settings"))

  implementation(libs.androidx.browser)
  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.window.core)
  implementation(libs.androidx.window)
  implementation(libs.bundles.m3.adaptive) {
    this.isTransitive = false
  }

  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.google.oss.licenses)
  implementation(libs.jakewharton.timber)
}
