package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.statemachine.builders.StateMachineBuilder
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.RootNode

open class StateMachine<A : Action, E : Event, S : State>(
    builder: StateMachineBuilder<A, E, S>.() -> Unit,
) {
    internal val rootNode: RootNode<A, E, S> = StateMachineBuilder<A, E, S>().apply(builder).build()
}
