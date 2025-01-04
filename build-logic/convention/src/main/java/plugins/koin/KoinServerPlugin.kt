package plugins.koin

import dsl.implementation
import dsl.kotlin
import dsl.ksp
import dsl.library
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class KoinServerPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("ksp").pluginId)

        kotlin {
            dependencies {
                implementation(libs.library("koin-core"))
                implementation(libs.library("koin-ktor"))
                implementation(libs.library("koin-annotations"))
                ksp(libs.library("koin-annotations-compiler"))
            }
        }
    }
}