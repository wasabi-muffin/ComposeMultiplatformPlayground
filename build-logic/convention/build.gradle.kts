plugins {
    `kotlin-dsl`
}

group = "jp.or.coop.kobe"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs += "-Xopt-in=org.jetbrains.kotlin.gradle.ExperimentalWasmDsl"
            jvmTarget = "17"
        }
    }
}

gradlePlugin {
    plugins {
        register("kotlinAndroid") {
            id = "build.kotlin.android"
            implementationClass = "plugins.kotlin.KotlinAndroidPlugin"
        }
        register("kotlinJvm") {
            id = "build.kotlin.jvm"
            implementationClass = "plugins.kotlin.KotlinJvmPlugin"
        }
        register("kotlinWasm") {
            id = "build.kotlin.wasm"
            implementationClass = "plugins.kotlin.KotlinWasmPlugin"
        }
        register("androidApplication") {
            id = "build.android.application"
            implementationClass = "plugins.android.AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = "build.android.library"
            implementationClass = "plugins.android.AndroidLibraryPlugin"
        }
        register("kmpAndroid") {
            id = "build.kmp.android"
            implementationClass = "plugins.kmp.KmpAndroidPlugin"
        }
        register("kmpIos") {
            id = "build.kmp.ios"
            implementationClass = "plugins.kmp.KmpIosPlugin"
        }
        register("kmpWasm") {
            id = "build.kmp.wasm"
            implementationClass = "plugins.kmp.KmpWasmPlugin"
        }
        register("kmpDesktop") {
            id = "build.kmp.desktop"
            implementationClass = "plugins.kmp.KmpDesktopPlugin"
        }
        register("composeKmp") {
            id = "build.compose.kmp"
            implementationClass = "plugins.compose.ComposeKmpPlugin"
        }
        register("composeAndroid") {
            id = "build.compose.android"
            implementationClass = "plugins.compose.ComposeAndroidPlugin"
        }
        register("composeDesktop") {
            id = "build.compose.desktop"
            implementationClass = "plugins.compose.ComposeDesktopPlugin"
        }
        register("koinKmp") {
            id = "build.koin.kmp"
            implementationClass = "plugins.koin.KoinKmpPlugin"
        }
        register("koinAndroid") {
            id = "build.koin.android"
            implementationClass = "plugins.koin.KoinAndroidPlugin"
        }
        register("koinServer") {
            id = "build.koin.server"
            implementationClass = "plugins.koin.KoinServerPlugin"
        }
        register("ktlint") {
            id = "build.tools.ktlint"
            implementationClass = "plugins.tools.KtlintPlugin"
        }
    }
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    compileOnly(gradleApi())
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.android)
    implementation(libs.gradle.compose)
    implementation(libs.gradle.ksp)
    implementation(libs.gradle.ktlint)
    implementation(libs.gradle.serialization)
    implementation(libs.gradle.sqldelight)
}
