package tech.fika.compose.multiplatform.playground.setup.presentation

import tech.fika.compose.multiplatform.playground.domain.core.DomainError
import tech.fika.compose.multiplatform.playground.domain.entities.Platform
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action

sealed class SetupAction : Action {
    data object OnStart : SetupAction()
    internal data object LoadPlatform : SetupAction()
    internal data class LoadPlatformSuccess(val platform: Platform) : SetupAction()
    internal data class LoadPlatformError(val error: DomainError) : SetupAction()
    data object ClickMe : SetupAction()
}