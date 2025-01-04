package plugins.kotlin

import dsl.configureWasm
import dsl.libs
import dsl.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KotlinWasmPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("kotlin-multiplatform").pluginId)

        configureWasm(
            module = "composeApp",
            outputFile = "composeApp.js"
        )
    }
}