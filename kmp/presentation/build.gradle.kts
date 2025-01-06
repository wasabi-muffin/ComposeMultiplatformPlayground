plugins {
    alias(libs.plugins.build.android.library)
    alias(libs.plugins.build.kmp.android)
    alias(libs.plugins.build.kmp.ios)
    alias(libs.plugins.build.kmp.wasm)
    alias(libs.plugins.build.kmp.desktop)
    alias(libs.plugins.build.compose.kmp)
    alias(libs.plugins.build.koin.kmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.log.kermit)
        }
        all {
            languageSettings {
                optIn("kotlin.uuid.ExperimentalUuidApi")
            }
        }
    }
}

android {
    namespace = "tech.fika.compose.multiplatform.playground.presentation"
}