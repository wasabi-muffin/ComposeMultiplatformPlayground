plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.build.koin.server)
    application
}

group = "tech.fika.compose.multiplatform.playground"
version = "1.0.0"

application {
    mainClass.set("tech.fika.compose.multiplatform.playground.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.server.config)
    implementation(projects.server.domain)
    implementation(projects.server.controller)
    implementation(projects.server.data.local)
    implementation(projects.server.data.remote)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.openapi)
    implementation(libs.ktor.server.sessions)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.websockets)
    implementation(libs.ktor.plugin.serialization)
    implementation(libs.koin.logger.slf4j)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.test.kotlin.junit)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
