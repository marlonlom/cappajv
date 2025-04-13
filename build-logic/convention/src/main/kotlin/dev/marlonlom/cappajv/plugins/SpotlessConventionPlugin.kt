/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.plugins

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Spotless convention plugin class.
 * @author marlonlom
 */
class SpotlessConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply("com.diffplug.spotless")

      extensions.configure<SpotlessExtension> {
        kotlin {
          target("**/*.kt")
          targetExclude("${layout.buildDirectory}/**/*.kt")
          ktlint().editorConfigOverride(
            mapOf(
              "android" to "true",
              "charset" to "utf-8",
              "end_of_line" to "lf",
              "indent_style" to "space",
              "indent_size" to 2,
              "insert_final_newline" to true,
              "max_line_length" to 120,
              "trim_trailing_whitespace" to true,
              "ij_continuation_indent_size" to 2,
              "ij_kotlin_name_count_to_use_star_import" to 999,
              "ij_kotlin_name_count_to_use_star_import_for_members" to 999,
              "ktlint_function_naming_ignore_when_annotated_with" to "Composable"
            ),
          )
          trimTrailingWhitespace()
          leadingTabsToSpaces()
          endWithNewline()
          licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }

        kotlinGradle {
          target("**/*.kts")
          targetExclude("**/build/**/*.kts")
          ktlint()
          trimTrailingWhitespace()
          leadingTabsToSpaces()
          endWithNewline()
          licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
        }

        format("xml") {
          target("**/*.xml")
          targetExclude("**/build/**/*.xml")
          trimTrailingWhitespace()
          leadingTabsToSpaces()
          endWithNewline()
          licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
        }
      }
    }
  }
}
