package plugins.compose

import dsl.compose
import dsl.kmp
import dsl.library
import dsl.libs
import dsl.plugin
import dsl.sourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class ComposeKmpPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("compose-multiplatform").pluginId)
        pluginManager.apply(libs.plugin("compose-compiler").pluginId)

        kmp {
            sourceSets {
                commonMain.dependencies {
                    implementation(libs.library("koin-compose"))
                    implementation(libs.library("koin-compose-viewmodel"))
                    implementation(libs.library("koin-compose-viewmodel-navigation"))
                    implementation(libs.library("compose-lifecycle-runtime"))
                    implementation(libs.library("compose-lifecycle-viewmodel"))
                    implementation(compose.runtime)
                    implementation(compose.foundation)
                    implementation(compose.material)
                    implementation(compose.ui)
                    implementation(compose.components.resources)
                    implementation(compose.components.uiToolingPreview)
                }
            }
        }
    }
}
