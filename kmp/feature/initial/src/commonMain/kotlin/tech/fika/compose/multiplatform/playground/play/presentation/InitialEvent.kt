package tech.fika.compose.multiplatform.playground.play.presentation

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event

sealed class InitialEvent : Event {
    data object NavigateSetup : InitialEvent()
}
