/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.plugins

import com.android.build.api.dsl.ApplicationExtension
import dev.marlonlom.cappajv.configs.Config
import dev.marlonlom.cappajv.extensions.configureAndroidKotlin
import dev.marlonlom.cappajv.extensions.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

/**
 * Android mobile app convention plugin class.
 * @author marlonlom
 */
class MobileAppConventionPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      with(pluginManager) {
        apply("com.android.application")
      }
      extensions.configure<ApplicationExtension> {
        defaultConfig.apply {
          targetSdk = Config.android.targetSdkVersion
          applicationId = Config.android.applicationId
          versionCode = Config.android.versionCode
          versionName = Config.android.versionName
          testInstrumentationRunner = Config.android.testInstrumentationRunner
        }
        buildFeatures {
          buildConfig = true
        }
        configureAndroidKotlin(this)
        configureBuildTypes(this)
      }
      tasks.withType<Test> {
        jvmArgs("-XX:+EnableDynamicAgentLoading")
      }
    }
  }
}
