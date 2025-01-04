package tech.fika.compose.multiplatform.playground.presentation.statemachine.builders

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.LifecycleDsl
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.LifecycleNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.ListenerNode

@LifecycleDsl
class LifecycleBuilder<A : Action, S : State> {
    private var onCreate: ListenerNode<A, S>.() -> Unit = {}
    private var onStart: ListenerNode<A, S>.() -> Unit = {}
    private var onResume: ListenerNode<A, S>.() -> Unit = {}
    private var onPause: ListenerNode<A, S>.() -> Unit = {}
    private var onStop: ListenerNode<A, S>.() -> Unit = {}
    private var onDestroy: () -> Unit = {}

    fun onCreate(block: ListenerNode<A, S>.() -> Unit) {
        onCreate = block
    }

    fun onStart(block: ListenerNode<A, S>.() -> Unit) {
        onStart = block
    }

    fun onResume(block: ListenerNode<A, S>.() -> Unit) {
        onResume = block
    }

    fun onPause(block: ListenerNode<A, S>.() -> Unit) {
        onPause = block
    }

    fun onStop(block: ListenerNode<A, S>.() -> Unit) {
        onStop = block
    }

    fun onDestroy(block: () -> Unit) {
        onDestroy = block
    }

    fun build() = LifecycleNode(
        onCreate = onCreate,
        onStart = onStart,
        onResume = onResume,
        onPause = onPause,
        onStop = onStop,
        onDestroy = onDestroy
    )
}