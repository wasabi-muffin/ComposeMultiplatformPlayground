package tech.fika.compose.multiplatform.playground.play.presentation

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action

sealed class InitialAction : Action {
    data object ClickNext : InitialAction()
}