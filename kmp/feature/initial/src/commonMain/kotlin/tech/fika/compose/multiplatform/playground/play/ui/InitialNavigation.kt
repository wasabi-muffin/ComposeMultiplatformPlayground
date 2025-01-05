package tech.fika.compose.multiplatform.playground.play.ui

import kotlinx.serialization.Serializable

@Serializable
data object InitialRoute

interface InitialNavigator {
    fun setup(name: String)
}