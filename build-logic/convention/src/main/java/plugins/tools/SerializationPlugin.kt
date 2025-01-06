package plugins.tools

import dsl.kmp
import dsl.library
import dsl.libs
import dsl.plugin
import dsl.sourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project

class SerializationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("kotlin-serialization").pluginId)

        kmp {
            sourceSets {
                commonMain.dependencies {
                    implementation(libs.library("kotlinx-serialization-json"))
                }
            }
        }
    }
}