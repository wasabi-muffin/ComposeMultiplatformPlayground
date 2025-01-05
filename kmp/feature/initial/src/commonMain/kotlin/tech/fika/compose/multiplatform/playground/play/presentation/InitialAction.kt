package tech.fika.compose.multiplatform.playground.play.presentation

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action

sealed class InitialAction : Action {
    data class InputText(val text: String) : InitialAction()
    data object ClickNext : InitialAction()
}