package plugins.koin

import dsl.kmp
import dsl.kmpKsp
import dsl.library
import dsl.libs
import dsl.plugin
import dsl.sourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class KoinKmpPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("ksp").pluginId)

        kmp {
            sourceSets {
                commonMain {
                    dependencies {
                        implementation(libs.library("koin-core"))
                        api(libs.library("koin-annotations"))
                    }
//                    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin/")
                }
            }
        }

//        tasks.withType(KotlinCompile::class).configureEach {
//            if (name != "kspCommonMainKotlinMetadata") {
//                dependsOn("kspCommonMainKotlinMetadata")
//            }
//        }

        dependencies {
            kmpKsp(libs.library("koin-annotations-compiler"))
        }
    }
}
