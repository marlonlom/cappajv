/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  @Suppress("UnstableApiUsage")
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "Cappajv"
include(
  ":apps:mobile",
  ":features:domain:catalog-home",
  ":features:domain:catalog-favorites",
  ":features:mobile:catalog-detail",
  ":features:mobile:catalog-favorites",
  ":features:mobile:catalog-home",
  ":features:mobile:designsystem",
  ":features:mobile:onboarding",
  ":features:mobile:settings",
)
include(":apps:tv")
include(":features:core:catalog", ":features:core:database", ":features:core:preferences")
