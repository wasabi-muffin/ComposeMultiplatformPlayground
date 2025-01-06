plugins {
    alias(libs.plugins.build.android.library)
    alias(libs.plugins.build.kmp.android)
    alias(libs.plugins.build.kmp.ios)
    alias(libs.plugins.build.kmp.wasm)
    alias(libs.plugins.build.kmp.desktop)
    alias(libs.plugins.build.koin.kmp)
    alias(libs.plugins.build.compose.kmp)
    alias(libs.plugins.build.tools.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.kmp.domain)
            implementation(projects.kmp.presentation)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "tech.fika.compose.multiplatform.playground.feature.initial"
}
