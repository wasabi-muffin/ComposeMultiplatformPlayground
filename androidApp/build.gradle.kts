plugins {
    alias(libs.plugins.build.kotlin.android)
    alias(libs.plugins.build.android.application)
    alias(libs.plugins.build.compose.android)
}

android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.kmp.domain)
    implementation(projects.kmp.app)
    implementation(libs.koin.android)
}
