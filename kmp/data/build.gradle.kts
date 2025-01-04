plugins {
    alias(libs.plugins.build.android.library)
    alias(libs.plugins.build.kmp.android)
    alias(libs.plugins.build.kmp.ios)
    alias(libs.plugins.build.kmp.wasm)
    alias(libs.plugins.build.kmp.desktop)
    alias(libs.plugins.build.koin.kmp)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.kmp.domain)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.settings.core)
            implementation(libs.settings.serialization)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.plugin.content.negotiation)
            implementation(libs.ktor.plugin.serialization)
            implementation(libs.ktor.plugin.logging)
            implementation(libs.settings.coroutines)
        }
        androidMain.dependencies {
            implementation(libs.settings.datastore)
        }
        nativeMain.dependencies {
            implementation(libs.settings.datastore)
        }
        desktopMain.dependencies {
            implementation(libs.settings.datastore)
        }
        wasmJsMain.dependencies {
            implementation(libs.kotlinx.browser)
        }
        all {
            languageSettings {
                optIn("com.russhwolf.settings.ExperimentalSettingsApi")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }
    }
}

android {
    namespace = "tech.fika.compose.multiplatform.playground.data"
}

