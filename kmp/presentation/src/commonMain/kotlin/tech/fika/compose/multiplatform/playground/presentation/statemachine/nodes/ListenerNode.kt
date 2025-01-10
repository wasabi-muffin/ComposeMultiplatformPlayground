package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

data class ListenerNode<A : Action, S : State>(
    val state: S,
    val dispatch: (A) -> Unit,
)
