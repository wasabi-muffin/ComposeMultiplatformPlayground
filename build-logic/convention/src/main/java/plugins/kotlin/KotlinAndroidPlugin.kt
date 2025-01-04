package plugins.kotlin

import dsl.kotlinAndroid
import dsl.libs
import dsl.plugin
import dsl.toJvmTarget
import dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KotlinAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("kotlin-android").pluginId)

        kotlinAndroid {
            compilerOptions {
                jvmTarget.set(libs.version("java").toJvmTarget())
            }
        }
    }
}