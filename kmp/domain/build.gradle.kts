plugins {
    alias(libs.plugins.build.android.library)
    alias(libs.plugins.build.kmp.android)
    alias(libs.plugins.build.kmp.ios)
    alias(libs.plugins.build.kmp.wasm)
    alias(libs.plugins.build.kmp.desktop)
    alias(libs.plugins.build.tools.serialization)
    alias(libs.plugins.build.koin.kmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "tech.fika.compose.multiplatform.playground.domain"
}
