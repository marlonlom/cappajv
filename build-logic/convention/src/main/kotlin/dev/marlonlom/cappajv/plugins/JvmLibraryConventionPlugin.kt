/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.plugins

import dev.marlonlom.cappajv.configs.Config
import dev.marlonlom.cappajv.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Jvm library convention plugin class.
 * @author marlonlom
 */
class JvmLibraryConventionPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      with(pluginManager) {
        apply("org.jetbrains.kotlin.jvm")
      }
      tasks.withType<KotlinCompile>().configureEach {
        @Suppress("DEPRECATION")
        kotlinOptions {
          jvmTarget = Config.jvm.kotlinJvm
        }
      }
      extensions.configure<JavaPluginExtension> {
        sourceCompatibility = Config.jvm.javaVersion
        targetCompatibility = Config.jvm.javaVersion
      }
      dependencies {
        add("testImplementation", versionCatalog().findLibrary("junit").get())
      }
    }
  }
}
