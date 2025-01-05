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
class ComposeDesktopPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("compose-multiplatform").pluginId)
        pluginManager.apply(libs.plugin("compose-compiler").pluginId)

        dependencies {
            implementation(libs.library("koin-compose"))
            implementation(libs.library("compose-lifecycle-runtime"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.desktop.currentOs)
            implementation(compose.preview)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
    }
}
