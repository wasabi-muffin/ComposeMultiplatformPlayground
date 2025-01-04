package tech.fika.compose.multiplatform.playground.setup.presentation

import tech.fika.compose.multiplatform.playground.domain.entities.Greeting
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

sealed class SetupState : State {
    data object Initial : SetupState()
    data object Loading : SetupState()
    data class Stable(val greeting: Greeting, val isShowContent: Boolean) : SetupState()
}