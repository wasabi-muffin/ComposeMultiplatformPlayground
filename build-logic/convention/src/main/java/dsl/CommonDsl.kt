package dsl

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.PluginContainer
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.the
import org.gradle.plugin.use.PluginDependency

internal fun PluginContainer.apply(plugin: Provider<PluginDependency>): Plugin<Any> = apply(plugin.get().pluginId)

internal fun PluginManager.apply(plugin: Provider<PluginDependency>) = apply(plugin.get().pluginId)
