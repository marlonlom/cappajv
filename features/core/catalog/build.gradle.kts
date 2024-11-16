/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id("java-library")
  alias(libs.plugins.kotlin.jvm)
  id("kotlinx-serialization")
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  @Suppress("DEPRECATION")
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.majorVersion
  }
}

dependencies {
  implementation(libs.kotlinx.serialization.json)
  testImplementation(libs.junit)
}
