package tech.fika.compose.multiplatform.playground.setup.ui

import kotlinx.serialization.Serializable

@Serializable
data class SetupRoute(val name: String = "")

interface SetupNavigator {
    fun back()
}