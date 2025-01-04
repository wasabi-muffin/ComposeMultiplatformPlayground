plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.build.koin.server)
}

dependencies {
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.test.kotlin.junit)
}
