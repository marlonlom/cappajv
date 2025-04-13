package dev.marlonlom.cappajv.plugins

import dev.marlonlom.cappajv.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Android koin convention plugin class.
 * @author marlonlom
 */
class AndroidKoinConventionPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      dependencies {
        val composeBom = platform(versionCatalog().findLibrary("koin-bom").get())
        add("implementation", composeBom)
        add("implementation", versionCatalog().findBundle("koin").get())
      }
    }
  }
}
