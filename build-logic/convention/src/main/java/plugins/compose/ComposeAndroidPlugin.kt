package plugins.compose

import dsl.compose
import dsl.debugImplementation
import dsl.implementation
import dsl.library
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class ComposeAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("compose-multiplatform").pluginId)
        pluginManager.apply(libs.plugin("compose-compiler").pluginId)

        dependencies {
            implementation(libs.library("koin-compose"))
            implementation(libs.library("androidx-lifecycle-runtime-compose"))
            implementation(libs.library("androidx-activity-compose"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            debugImplementation(compose.uiTooling)
        }
    }
}
