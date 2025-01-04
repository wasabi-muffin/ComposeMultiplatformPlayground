package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import tech.fika.compose.multiplatform.playground.presentation.core.components.Processor
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.statemachine.builders.Matcher
import tech.fika.compose.multiplatform.playground.presentation.statemachine.builders.StateMachineBuilder
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.RootNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.StateNode

open class StateMachine<A : Action, E : Event, S : State>(
    private val rootNode: RootNode<A, E, S>,
) {
    constructor(builder: StateMachineBuilder<A, E, S>.() -> Unit) : this(
        StateMachineBuilder<A, E, S>().apply(builder).build().rootNode
    )

    val initialState: S get() = rootNode.initialState ?: error("Initial State is not set")
    val stateMap: Map<Matcher<S, S>, StateNode<A, E, S>> = rootNode.stateMap
    val processor: Processor<A, E, S> get() = StateMachineProcessor(stateMachine = this)
    val lifecycleNode = rootNode.lifecycleNode
    val interceptors = rootNode.interceptorNode.interceptors
    val jobHandler = rootNode.jobHandler
    val messageManager = rootNode.messageManager
    val messageHandler = rootNode.messageHandler
}
