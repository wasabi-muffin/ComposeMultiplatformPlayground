package dsl

import com.android.build.gradle.TestedExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroid(block: TestedExtension.() -> Unit = {}) {
    android {
        namespace?.let {
            this.namespace = it
        }
        compileSdkVersion(libs.version("android-compileSdk").toInt())
        buildToolsVersion = libs.version("android-buildTools")

        compileOptions {
            sourceCompatibility = libs.version("java").toJavaVersion()
            targetCompatibility = libs.version("java").toJavaVersion()
            isCoreLibraryDesugaringEnabled = true
        }

        defaultConfig {
            minSdk = libs.version("android-minSdk").toInt()
            targetSdk = libs.version("android-targetSdk").toInt()
        }

        dependencies {
            coreLibraryDesugaring(libs.library("android-desugarJdkLibs"))
        }

        block()
    }
}
