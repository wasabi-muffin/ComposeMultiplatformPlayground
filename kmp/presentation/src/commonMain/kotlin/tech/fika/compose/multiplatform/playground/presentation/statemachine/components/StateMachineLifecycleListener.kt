package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import tech.fika.compose.multiplatform.playground.presentation.core.lifecycle.LifecycleListener
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.ListenerNode

class StateMachineLifecycleListener<A : Action, E : Event, S : State>(
    private val stateMachine: StateMachine<A, E, S>,
) : LifecycleListener<A, S> {
    override fun onCreate(state: S, dispatch: (A) -> Unit) =
        stateMachine.lifecycleNode.onCreate(ListenerNode(state = state, dispatch = dispatch))

    override fun onStart(state: S, dispatch: (A) -> Unit) =
        stateMachine.lifecycleNode.onStart(ListenerNode(state = state, dispatch = dispatch))

    override fun onResume(state: S, dispatch: (A) -> Unit) =
        stateMachine.lifecycleNode.onResume(ListenerNode(state = state, dispatch = dispatch))

    override fun onPause(state: S, dispatch: (A) -> Unit) =
        stateMachine.lifecycleNode.onPause(ListenerNode(state = state, dispatch = dispatch))

    override fun onStop(state: S, dispatch: (A) -> Unit) =
        stateMachine.lifecycleNode.onStop(ListenerNode(state = state, dispatch = dispatch))

    override fun onDestroy() = stateMachine.lifecycleNode.onDestroy()
}

