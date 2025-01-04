plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.build.koin.server)
}

dependencies {
    implementation(projects.server.domain)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    testImplementation(libs.test.kotlin.junit)
}
