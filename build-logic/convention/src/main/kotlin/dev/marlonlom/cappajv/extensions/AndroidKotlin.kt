/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.extensions

import com.android.build.api.dsl.CommonExtension
import dev.marlonlom.cappajv.configs.AndroidConfig
import dev.marlonlom.cappajv.configs.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

/**
 * Extension function for configuring android kotlin module.
 * @author marlonlom
 *
 * @param extension Common extension instance.
 * @param androidConfig Android configuration information.
 *
 */
internal fun Project.configureAndroidKotlin(
  extension: CommonExtension<*, *, *, *, *, *>,
  androidConfig: AndroidConfig = Config.android,
) {
  with(extension) {
    namespace = androidConfig.nameSpace
    compileSdk = androidConfig.compileSdkVersion

    defaultConfig.apply {
      minSdk = androidConfig.minSdkVersion
      testInstrumentationRunner = androidConfig.testInstrumentationRunner
      vectorDrawables.useSupportLibrary = true
    }

    compileOptions.sourceCompatibility = Config.jvm.javaVersion
    compileOptions.targetCompatibility = Config.jvm.javaVersion

    packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

    dependencies {
      add("implementation", versionCatalog().findLibrary("androidx-core-ktx").get())
      add("implementation", versionCatalog().findLibrary("kotlinx-coroutines-android").get())
      add("implementation", versionCatalog().findLibrary("kotlinx-coroutines-core").get())

      add("testImplementation", versionCatalog().findLibrary("junit").get())
      add("testImplementation", versionCatalog().findLibrary("kotlinx-coroutines-test").get())
      add("testImplementation", versionCatalog().findLibrary("mockk").get())

      add("androidTestImplementation", versionCatalog().findLibrary("androidx-test-espresso-core").get())
      add("androidTestImplementation", versionCatalog().findLibrary("androidx-test-ext-junit").get())
      add("androidTestImplementation", versionCatalog().findLibrary("google-truth").get())
    }
  }
  tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
      jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(Config.jvm.kotlinJvm))
    }
  }
}
