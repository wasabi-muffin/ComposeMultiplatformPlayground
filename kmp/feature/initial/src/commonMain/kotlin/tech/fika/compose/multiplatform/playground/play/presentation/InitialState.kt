package tech.fika.compose.multiplatform.playground.play.presentation

import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

sealed class InitialState : State {
    abstract val text: String

    data class Initial(override val text: String) : InitialState()
}