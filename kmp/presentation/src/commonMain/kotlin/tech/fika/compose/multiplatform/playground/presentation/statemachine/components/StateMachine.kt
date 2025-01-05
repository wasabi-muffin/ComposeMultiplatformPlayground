package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.tools.DefaultJobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.tools.JobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageManager
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

    constructor(
        jobHandler: JobHandler = DefaultJobHandler(),
        messageManager: MessageManager? = null,
        builder: StateMachineBuilder<A, E, S>.() -> Unit,
    ) : this(
        StateMachineBuilder<A, E, S>(
            jobHandler = jobHandler,
            messageManager = messageManager,
        ).apply(builder).build().rootNode
    )

    val initialState: S get() = rootNode.initialState ?: error("Initial State is not set")
    internal val stateMap: Map<Matcher<S, S>, StateNode<A, E, S>> = rootNode.stateMap
    internal val lifecycleNode = rootNode.lifecycleNode
    internal val interceptors = rootNode.interceptorNode.interceptors
    internal val jobHandler = rootNode.jobHandler
    val messageManager = rootNode.messageManager
}
