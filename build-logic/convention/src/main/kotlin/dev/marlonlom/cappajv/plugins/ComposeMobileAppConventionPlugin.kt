/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.plugins

import com.android.build.api.dsl.ApplicationExtension
import dev.marlonlom.cappajv.extensions.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * Android compose mobile app convention plugin class.
 * @author marlonlom
 */
class ComposeMobileAppConventionPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      with(pluginManager) {
        apply("com.android.application")
        apply("org.jetbrains.kotlin.plugin.compose")
      }

      val extension = extensions.getByType<ApplicationExtension>()
      configureAndroidCompose(extension)
    }
  }
}
