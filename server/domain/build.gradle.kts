plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.build.koin.server)
}

dependencies {
    implementation(libs.logback)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.test.kotlin.junit)
}
