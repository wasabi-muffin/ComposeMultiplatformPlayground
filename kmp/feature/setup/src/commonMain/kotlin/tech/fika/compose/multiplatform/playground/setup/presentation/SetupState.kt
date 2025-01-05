package tech.fika.compose.multiplatform.playground.setup.presentation

import tech.fika.compose.multiplatform.playground.domain.entities.Greeting
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

sealed class SetupState : State {
    abstract val name: String

    data class Initial(override val name: String) : SetupState()
    data class Loading(override val name: String) : SetupState()
    data class Stable(
        val greeting: Greeting,
        val isShowContent: Boolean,
        override val name: String,
    ) : SetupState()
}