package plugins.koin

import dsl.implementation
import dsl.ksp
import dsl.library
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class KoinAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("ksp").pluginId)

        dependencies {
            implementation(libs.library("koin-core"))
            implementation(libs.library("koin-android"))
            implementation(libs.library("koin-annotations"))
            ksp(libs.library("koin-annotations-compiler"))
        }
    }
}