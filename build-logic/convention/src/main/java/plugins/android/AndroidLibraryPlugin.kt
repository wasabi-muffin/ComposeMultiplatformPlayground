package plugins.android

import dsl.androidLibrary
import dsl.configureAndroid
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("android-library").pluginId)

        androidLibrary {
            configureAndroid()

            defaultConfig {
                consumerProguardFiles("consumer-rules.pro")
            }
        }
    }
}