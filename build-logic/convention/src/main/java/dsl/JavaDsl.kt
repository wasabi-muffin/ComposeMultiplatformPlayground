package dsl

import org.gradle.api.JavaVersion
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal fun String.toJavaVersion(): JavaVersion = JavaVersion.toVersion(this.toInt())

internal fun String.toJvmTarget(): JvmTarget = JvmTarget.fromTarget(this)

internal fun String.toJavaLanguageVersion(): JavaLanguageVersion = JavaLanguageVersion.of(this)