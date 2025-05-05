/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("cappajv.android.library")
  id("cappajv.spotless")
}

android {
  namespace = "dev.marlonlom.cappajv.domain.catalog.favorites"
}

dependencies {
  implementation(project(":features:core:catalog"))
  implementation(project(":features:core:database"))
}
