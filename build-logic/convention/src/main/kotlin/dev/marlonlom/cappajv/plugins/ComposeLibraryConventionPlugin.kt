/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.plugins

import com.android.build.api.dsl.LibraryExtension
import dev.marlonlom.cappajv.extensions.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * Android compose library convention plugin class.
 * @author marlonlom
 */
class ComposeLibraryConventionPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.plugin.compose")
      }

      val extension = extensions.getByType<LibraryExtension>()
      configureAndroidCompose(extension)
    }
  }
}
