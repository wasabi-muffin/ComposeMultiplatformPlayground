plugins {
    alias(libs.plugins.build.android.library)
    alias(libs.plugins.build.kmp.android)
    alias(libs.plugins.build.kmp.ios)
    alias(libs.plugins.build.kmp.wasm)
    alias(libs.plugins.build.kmp.desktop)
    alias(libs.plugins.build.koin.kmp)
    alias(libs.plugins.build.compose.kmp)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.kmp.domain)
            implementation(projects.kmp.presentation)
            implementation(projects.kmp.data)
            implementation(projects.kmp.feature.initial)
            implementation(projects.kmp.feature.setup)
            implementation(libs.compose.navigation)
        }
        androidMain.dependencies {
            implementation(compose.preview)
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

android {
    namespace = "tech.fika.compose.multiplatform.playground.compose"
}

dependencies {
    implementation(libs.compose.navigation)
    debugImplementation(compose.uiTooling)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

