package plugins.android

import dsl.androidApp
import dsl.configureAndroid
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

@Suppress("unused")
class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("android-application").pluginId)

        androidApp {
            configureAndroid()

            namespace = "tech.fika.compose.multiplatform.playground"

            defaultConfig {
                applicationId = "tech.fika.compose.multiplatform.playground"
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                }
            }
        }
    }
}