package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

data class StateListenerNode<A : Action, S : State>(
    var onEnter: (ListenerNode<A, S>) -> Unit = {},
    var onRepeat: (ListenerNode<A, S>) -> Unit = {},
    var onExit: (ListenerNode<A, S>) -> Unit = {},
)
