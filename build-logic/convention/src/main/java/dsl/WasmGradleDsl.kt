package dsl

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinWasmTargetDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

@OptIn(ExperimentalWasmDsl::class)
internal fun Project.configureWasm(
    module: String? = null,
    outputFile: String? = null,
    block: KotlinWasmTargetDsl.() -> Unit = {},
) {
    kmp {
        wasmJs {
            moduleName = module
            browser {
                val rootDirPath = project.rootDir.path
                val projectDirPath = project.projectDir.path
                commonWebpackConfig {
                    outputFileName = outputFile
                    devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                        static = (static ?: mutableListOf()).apply {
                            // Serve sources to debug inside browser
                            add(rootDirPath)
                            add(projectDirPath)
                        }
                    }
                }
            }
            binaries.executable()

            block()
        }

        sourceSets.wasmJsMain {
            languageSettings {
                optIn("androidx.compose.ui.ExperimentalComposeUiApi")
            }
        }
    }
}