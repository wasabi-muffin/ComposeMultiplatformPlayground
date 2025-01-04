package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

data class StateListenerNode<A : Action, S : State>(
    val onEnter: (ListenerNode<A, S>) -> Unit,
    val onRepeat: (ListenerNode<A, S>) -> Unit,
    val onExit: (ListenerNode<A, S>) -> Unit,
)