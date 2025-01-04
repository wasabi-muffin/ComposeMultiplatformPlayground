plugins {
    alias(libs.plugins.build.kotlin.wasm)
    alias(libs.plugins.build.compose.kmp)
}

kotlin {
    sourceSets {
        wasmJsMain {
            dependencies {
                implementation(projects.kmp.domain)
                implementation(projects.kmp.app)
                implementation(libs.koin.core)
            }
        }
    }
}
