/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

@file:Suppress("DSL_SCOPE_VIOLATION")

buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath(libs.google.oss.licenses.plugin) {
      exclude(group = "com.google.protobuf")
    }
    classpath(libs.kotlin.gradle.plugin)
    classpath(libs.kotlin.serialization.plugin)
  }
}

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlin.serialization) apply false
  alias(libs.plugins.google.devtools.ksp) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.spotless) apply false
}

/**
 * Spotless plugin configuration
 */
subprojects {
  apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
  configure<com.diffplug.gradle.spotless.SpotlessExtension> {
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
      indentWithSpaces()
      endWithNewline()
      licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
    }

    kotlinGradle {
      target("**/*.kts")
      targetExclude("**/build/**/*.kts")
      ktlint()
      trimTrailingWhitespace()
      indentWithSpaces()
      endWithNewline()
      licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
    }

    format("xml") {
      target("**/*.xml")
      targetExclude("**/build/**/*.xml")
      trimTrailingWhitespace()
      indentWithSpaces()
      endWithNewline()
      licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
    }
  }
}
