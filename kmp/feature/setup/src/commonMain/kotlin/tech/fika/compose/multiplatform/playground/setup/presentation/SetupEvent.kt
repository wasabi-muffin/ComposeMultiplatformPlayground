package tech.fika.compose.multiplatform.playground.setup.presentation

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event

sealed class SetupEvent : Event {
    data object NavigateBack : SetupEvent()
}