package tech.fika.compose.multiplatform.playground.play.presentation

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event

sealed class InitialEvent : Event {
    data class NavigateSetup(val text: String) : InitialEvent()
}
