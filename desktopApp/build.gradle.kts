import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.build.kotlin.jvm)
    alias(libs.plugins.build.compose.desktop)
}

dependencies {
    implementation(libs.kotlinx.coroutines.swing)
    implementation(projects.kmp.domain)
    implementation(projects.kmp.app)
    implementation(libs.koin.core)
}

compose {
    desktop {
        application {
            mainClass = "tech.fika.compose.multiplatform.playground.MainKt"

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "tech.fika.compose.multiplatform.playground"
                packageVersion = "1.0.0"
            }
        }
    }
}
