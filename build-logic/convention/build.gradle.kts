/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

group = "dev.marlonlom.cappajv.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
  @Suppress("DEPRECATION")
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.compose.compiler.gradlePlugin)
  compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("androidApp") {
      id = "cappajv.android.application.mobile"
      implementationClass = "dev.marlonlom.cappajv.plugins.MobileAppConventionPlugin"
    }
    register("androidAppCompose") {
      id = "cappajv.android.app.compose.mobile"
      implementationClass = "dev.marlonlom.cappajv.plugins.ComposeMobileAppConventionPlugin"
    }
    register("androidCoil") {
      id = "cappajv.android.coil"
      implementationClass = "dev.marlonlom.cappajv.plugins.AndroidCoilConventionPlugin"
    }
    register("androidKoin") {
      id = "cappajv.android.koin"
      implementationClass = "dev.marlonlom.cappajv.plugins.AndroidKoinConventionPlugin"
    }
    register("androidLibCompose") {
      id = "cappajv.android.lib.compose"
      implementationClass = "dev.marlonlom.cappajv.plugins.ComposeLibraryConventionPlugin"
    }
    register("androidLib") {
      id = "cappajv.android.library"
      implementationClass = "dev.marlonlom.cappajv.plugins.MobileLibConventionPlugin"
    }
    register("jvmLibrary") {
      id = "cappajv.jvm.lib"
      implementationClass = "dev.marlonlom.cappajv.plugins.JvmLibraryConventionPlugin"
    }
    register("spotless") {
      id = "cappajv.spotless"
      implementationClass = "dev.marlonlom.cappajv.plugins.SpotlessConventionPlugin"
    }
  }
}
