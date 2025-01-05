package dsl

import app.cash.sqldelight.gradle.SqlDelightExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jlleitschuh.gradle.ktlint.KtlintExtension

internal fun Project.java(action: JavaPluginExtension.() -> Unit) = extensions.configure(action)

internal fun Project.kotlin(action: KotlinProjectExtension.() -> Unit) =
    extensions.configure(action)

internal fun Project.kotlinAndroid(action: KotlinAndroidProjectExtension.() -> Unit) =
    extensions.configure(action)

internal fun Project.kotlinJs(action: KotlinJsProjectExtension.() -> Unit) =
    extensions.configure(action)

internal fun Project.kmp(block: KotlinMultiplatformExtension.() -> Unit) =
    extensions.configure(block)

internal fun Project.android(block: TestedExtension.() -> Unit) = extensions.configure(block)

internal fun Project.androidLibrary(block: LibraryExtension.() -> Unit) =
    extensions.configure(block)

internal fun Project.androidApp(block: BaseAppModuleExtension.() -> Unit) =
    extensions.configure(block)

internal fun Project.androidBase(block: BaseExtension.() -> Unit) = extensions.configure(block)

internal fun Project.ksp(block: KspExtension.() -> Unit) = extensions.configure(block)

internal fun Project.ktlint(block: KtlintExtension.() -> Unit) = extensions.configure(block)

internal fun Project.sqlDelight(block: SqlDelightExtension.() -> Unit) = extensions.configure(block)

internal val Project.compose: ComposePlugin.Dependencies
    get() = ComposePlugin.Dependencies(project)

internal fun KotlinMultiplatformExtension.sourceSets(block: NamedDomainObjectContainer<KotlinSourceSet>.() -> Unit) =
    sourceSets.block()
