package tech.fika.compose.multiplatform.playground.play.presentation

import kotlinx.serialization.Serializable
import tech.fika.compose.multiplatform.playground.domain.core.DomainError
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

@Serializable
sealed class InitialState : State {
    abstract val text: String

    @Serializable
    data class Initial(override val text: String, val error: DomainError = DomainError.Unknown()) : InitialState()
}
