/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("cappajv.android.library")
  id("cappajv.android.lib.compose")
  id("cappajv.android.koin")
  id("cappajv.spotless")
  alias(libs.plugins.google.devtools.ksp)
}

android {
  namespace = "dev.marlonlom.cappajv.core.database"

  defaultConfig {
    consumerProguardFiles("consumer-rules.pro")
  }
}

dependencies {
  implementation(libs.androidx.appcompat)
  implementation(libs.bundles.database.room)

  ksp(libs.androidx.room.compiler)
}
