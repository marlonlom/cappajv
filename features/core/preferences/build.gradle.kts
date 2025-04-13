/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("cappajv.android.library")
  id("cappajv.android.lib.compose")
  id("cappajv.android.koin")
  id("cappajv.spotless")
}

android {
  namespace = "dev.marlonlom.cappajv.core.preferences"

  defaultConfig {
    consumerProguardFiles("consumer-rules.pro")
  }
}

dependencies {
  implementation(libs.androidx.datastore.preferences)
}
