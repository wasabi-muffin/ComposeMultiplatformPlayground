rootProject.name = "ComposeMultiplatformPlayground"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":androidApp")
include(":desktopApp")
include(":webApp")
include(":kmp:app")
include(":kmp:domain")
include(":kmp:data")
include(":kmp:presentation")
include(":kmp:feature:initial")
include(":kmp:feature:setup")
include(":server:app")
include(":server:config")
include(":server:domain")
include(":server:controller")
include(":server:data:local")
include(":server:data:remote")
