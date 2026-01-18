/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Extension function for configuring android tv compose module.
 * @author marlonlom
 *
 * @param extension Common extension instance.
 */
internal fun Project.configureAndroidTvCompose(
  extension: CommonExtension,
) {
  val versionCatalog = versionCatalog()
  extension.apply {

    buildFeatures.compose = true

    dependencies {
      val composeBom = platform(versionCatalog.findLibrary("androidx-compose-bom").get())
      add("implementation", composeBom)
      add("androidTestImplementation", composeBom)

      add("implementation", versionCatalog.findLibrary("androidx-activity-compose").get())
      add("implementation", versionCatalog.findLibrary("androidx-appcompat").get())
      add("implementation", versionCatalog.findLibrary("androidx-compose-material-icons-extended").get())
      add("implementation", versionCatalog.findLibrary("androidx-compose-ui").get())
      add("implementation", versionCatalog.findLibrary("androidx-compose-ui-graphics").get())
      add("implementation", versionCatalog.findLibrary("androidx-compose-ui-tooling-preview").get())
      add("implementation", versionCatalog.findLibrary("androidx-tv-foundation").get())
      add("implementation", versionCatalog.findLibrary("androidx-tv-material").get())

      add("androidTestImplementation", versionCatalog.findLibrary("androidx-compose-ui-test-junit4").get())

      add("debugImplementation", versionCatalog.findLibrary("androidx-compose-ui-tooling").get())
      add(
        "debugImplementation", versionCatalog.findLibrary("androidx-compose-ui-test-manifest").get()
      )
    }
  }
}
