plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.build.koin.server)
}

dependencies {
    implementation(projects.server.domain)
    implementation(projects.server.config)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.logback)
    implementation(libs.postgresql)
    implementation(libs.hikaricp)
    implementation(libs.sqldelight.jdbc.driver)
    testImplementation(libs.test.kotlin.junit)
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("tech.fika.compose.multiplatform.playground.database")
            dialect(libs.sqldelight.dialect.server)
        }
    }
}
