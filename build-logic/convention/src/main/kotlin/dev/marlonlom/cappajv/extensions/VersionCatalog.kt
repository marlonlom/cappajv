/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.cappajv.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Returns the version catalog instance.
 * @author marlonlom
 *
 * @return Version catalog instance.
 */
internal fun Project.versionCatalog(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
