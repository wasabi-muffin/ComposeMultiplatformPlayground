package plugins.kmp

import dsl.kmp
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpDesktopPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("kotlin-multiplatform").pluginId)

        kmp {
            jvm("desktop")
        }
    }
}