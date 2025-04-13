/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

/**
 * Extension function for configuring android module build types
 * @author marlonlom
 *
 * @param extension Common extension instance.
 */
internal fun Project.configureBuildTypes(
  extension: CommonExtension<*, *, *, *, *, *>
) {
  extension.apply {
    buildTypes {
      getByName("release") {
        isShrinkResources = true
        isMinifyEnabled = true
        this.proguardFiles(
          getDefaultProguardFile("proguard-android-optimize.txt"),
          "proguard-rules.pro"
        )
      }
    }
  }
}
