package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

data class LifecycleNode<A : Action, S : State>(
    val onCreate: (ListenerNode<A, S>) -> Unit = {},
    val onStart: (ListenerNode<A, S>) -> Unit = {},
    val onResume: (ListenerNode<A, S>) -> Unit = {},
    val onPause: (ListenerNode<A, S>) -> Unit = {},
    val onStop: (ListenerNode<A, S>) -> Unit = {},
    val onDestroy: () -> Unit = {},
)