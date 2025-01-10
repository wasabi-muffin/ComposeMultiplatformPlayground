package tech.fika.compose.multiplatform.playground.presentation.statemachine.builders

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.ListenerNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.StateListenerNode

@Suppress("unused")
@StateListenerDsl
class StateListenerBuilder<A : Action, S : State> {
    private var onEnter: (ListenerNode<A, S>) -> Unit = {}
    private var onRepeat: (ListenerNode<A, S>) -> Unit = {}
    private var onExit: (ListenerNode<A, S>) -> Unit = {}

    fun onEnter(block: ListenerNode<A, S>.() -> Unit) {
        onEnter = block
    }

    fun onRepeat(block: ListenerNode<A, S>.() -> Unit) {
        onRepeat = block
    }

    fun onExit(block: ListenerNode<A, S>.() -> Unit) {
        onExit = block
    }

    fun build() = StateListenerNode(
        onEnter = onEnter,
        onRepeat = onRepeat,
        onExit = onExit
    )
}