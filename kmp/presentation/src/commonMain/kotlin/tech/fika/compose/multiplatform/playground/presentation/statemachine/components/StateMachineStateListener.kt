package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import tech.fika.compose.multiplatform.playground.presentation.core.components.StateListener
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.ListenerNode

class StateMachineStateListener<A : Action, E : Event, S : State>(
    private val stateMachine: StateMachine<A, E, S>,
) : StateListener<A, S> {
    private fun getNode(state: S) = stateMachine.stateMap.entries
        .find { stateMatcher -> stateMatcher.key.matches(value = state) }
        ?.value
        ?.stateListenerNode

    override fun onEnter(state: S, dispatch: (A) -> Unit) =
        getNode(state = state)?.onEnter?.invoke(ListenerNode(state = state, dispatch = dispatch)) ?: Unit

    override fun onRepeat(state: S, dispatch: (A) -> Unit) =
        getNode(state = state)?.onRepeat?.invoke(ListenerNode(state = state, dispatch = dispatch)) ?: Unit

    override fun onExit(state: S, dispatch: (A) -> Unit) =
        getNode(state = state)?.onExit?.invoke(ListenerNode(state = state, dispatch = dispatch)) ?: Unit
}
