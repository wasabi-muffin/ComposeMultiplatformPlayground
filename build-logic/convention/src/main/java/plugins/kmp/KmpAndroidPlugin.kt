package plugins.kmp

import dsl.configureAndroid
import dsl.kmp
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class KmpAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("kotlin-multiplatform").pluginId)

        configureAndroid()

        kmp {
            applyDefaultHierarchyTemplate()

            androidTarget {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
        }
    }
}