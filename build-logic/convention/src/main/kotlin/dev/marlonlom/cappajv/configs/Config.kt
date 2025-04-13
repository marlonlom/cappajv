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
    targetSdkVersion = 35,
    compileSdkVersion = 35,
    applicationId = "dev.marlonlom.cappajv",
    versionCode = 1,
    versionName = "1.0.0",
    nameSpace = "dev.marlonlom.cappajv"
  )

  /** TV android app Config. */
  val tv = AndroidConfig(
    minSdkVersion = 24,
    targetSdkVersion = 35,
    compileSdkVersion = 35,
    applicationId = "dev.marlonlom.cappajv.tv",
    versionCode = 1,
    versionName = "1.0.0",
    nameSpace = "dev.marlonlom.cappajv.tv"
  )

  /** JVM Config. */
  val jvm = JvmConfig(
    javaVersion = JavaVersion.VERSION_17,
    kotlinJvm = JavaVersion.VERSION_17.toString(),
  )
}

/**
 * Android specific config data class.
 * @author marlonlom
 *
 * @property minSdkVersion Min sdk version number.
 * @property targetSdkVersion Target sdk version number.
 * @property compileSdkVersion Compile sdk version number.
 * @property applicationId Application id as package text.
 * @property versionCode Version code number.
 * @property versionName Version name text.
 * @property nameSpace Application namespace as package text.
 */
data class AndroidConfig(
  val minSdkVersion: Int,
  val targetSdkVersion: Int,
  val compileSdkVersion: Int,
  val applicationId: String,
  val versionCode: Int,
  val versionName: String,
  val nameSpace: String
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
