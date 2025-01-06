package tech.fika.compose.multiplatform.playground.setup.presentation

import kotlinx.serialization.Serializable
import tech.fika.compose.multiplatform.playground.domain.entities.Greeting
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

@Serializable
sealed class SetupState : State {
    abstract val name: String

    @Serializable
    data class Initial(override val name: String) : SetupState()

    @Serializable
    data class Loading(override val name: String) : SetupState()

    @Serializable
    data class Stable(
        val greeting: Greeting,
        val isShowContent: Boolean,
        override val name: String,
    ) : SetupState()
}