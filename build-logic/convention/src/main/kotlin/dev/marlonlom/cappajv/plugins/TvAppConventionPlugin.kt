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
 * Android tv app convention plugin class.
 * @author marlonlom
 */
class TvAppConventionPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      with(pluginManager) {
        apply("com.android.application")
      }
      extensions.configure<ApplicationExtension> {
        defaultConfig.apply {
          targetSdk = Config.tv.targetSdkVersion
          applicationId = Config.tv.applicationId
          versionCode = Config.tv.versionCode
          versionName = Config.tv.versionName
          testInstrumentationRunner = Config.tv.testInstrumentationRunner
        }
        buildFeatures {
          buildConfig = true
        }
        configureAndroidKotlin(this, Config.tv)
        configureBuildTypes(this)
      }
      tasks.withType<Test> {
        jvmArgs("-XX:+EnableDynamicAgentLoading")
      }
    }
  }
}
