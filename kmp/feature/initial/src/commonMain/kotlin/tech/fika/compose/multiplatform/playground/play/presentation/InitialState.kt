package tech.fika.compose.multiplatform.playground.play.presentation

import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

sealed class InitialState : State {
    data object Initial : InitialState()
}