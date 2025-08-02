/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.configs

import org.gradle.api.JavaVersion

/**
 * Base config object.
 * @author marlonlom
 */
object Config {

  /** Mobile android app Config. */
  val android = AndroidConfig(
    minSdkVersion = 24,
    targetSdkVersion = 36,
    compileSdkVersion = 36,
    applicationId = "dev.marlonlom.cappajv",
    versionCode = 1,
    versionName = "1.0.0",
    nameSpace = "dev.marlonlom.cappajv",
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  )

  /** TV android app Config. */
  val tv = AndroidConfig(
    minSdkVersion = 24,
    targetSdkVersion = 36,
    compileSdkVersion = 36,
    applicationId = "dev.marlonlom.cappajv.tv",
    versionCode = 1,
    versionName = "1.0.0",
    nameSpace = "dev.marlonlom.cappajv.tv",
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  )

  /** JVM Config. */
  val jvm = JvmConfig(
    javaVersion = JavaVersion.VERSION_17,
    kotlinJvm = JavaVersion.VERSION_17.toString(),
  )
}

/**
 * Configuration data class for Android application settings.
 *
 * @author marlonlom
 *
 * @property minSdkVersion The minimum SDK version required to run the application.
 * @property targetSdkVersion The SDK version that the application targets.
 * @property compileSdkVersion The SDK version used to compile the application.
 * @property applicationId The unique application ID.
 * @property versionCode The version code of the application.
 * @property versionName The version name of the application.
 * @property nameSpace The namespace for the Android project.
 * @property testInstrumentationRunner The instrumentation runner for tests.
 */
data class AndroidConfig(
  val minSdkVersion: Int,
  val targetSdkVersion: Int,
  val compileSdkVersion: Int,
  val applicationId: String,
  val versionCode: Int,
  val versionName: String,
  val nameSpace: String,
  val testInstrumentationRunner: String
)

/**
 * JVM Config object.
 * @author marlonlom
 *
 * @property javaVersion Enum value for [JavaVersion].
 * @property kotlinJvm JVM major version code.
 */
data class JvmConfig(
  val javaVersion: JavaVersion,
  val kotlinJvm: String,
)
