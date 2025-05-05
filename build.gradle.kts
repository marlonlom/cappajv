/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

@file:Suppress("DSL_SCOPE_VIOLATION")

buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath(libs.google.oss.licenses.plugin) {
      exclude(group = "com.google.protobuf")
    }
    classpath(libs.kotlin.serialization.plugin)
  }
}

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlin.serialization) apply false
  alias(libs.plugins.google.devtools.ksp) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.spotless) apply false
}
