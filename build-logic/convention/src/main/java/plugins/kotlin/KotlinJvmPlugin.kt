package plugins.kotlin

import dsl.androidTestImplementationPlatform
import dsl.implementationPlatform
import dsl.kotlinAndroid
import dsl.library
import dsl.libs
import dsl.plugin
import dsl.toJvmTarget
import dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class KotlinJvmPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("kotlin-jvm").pluginId)
    }
}