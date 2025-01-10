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
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.plugin.content.negotiation)
            implementation(libs.ktor.plugin.serialization)
            implementation(libs.ktor.plugin.logging)
        }
    }
}

android {
    namespace = "tech.fika.compose.multiplatform.playground.remote"
}
