plugins {
    alias(libs.plugins.build.android.library)
    alias(libs.plugins.build.kmp.android)
    alias(libs.plugins.build.kmp.ios)
    alias(libs.plugins.build.kmp.wasm)
    alias(libs.plugins.build.kmp.desktop)
    alias(libs.plugins.build.koin.kmp)
    alias(libs.plugins.build.tools.serialization)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.kmp.domain)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.settings.core)
            implementation(libs.settings.serialization)
            implementation(libs.settings.coroutines)
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
    namespace = "tech.fika.compose.multiplatform.playground.local"
}

