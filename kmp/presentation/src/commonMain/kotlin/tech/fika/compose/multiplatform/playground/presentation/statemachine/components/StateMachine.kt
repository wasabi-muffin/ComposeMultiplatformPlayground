package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import tech.fika.compose.multiplatform.playground.presentation.core.components.Processor
import tech.fika.compose.multiplatform.playground.presentation.core.components.StateListener
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.lifecycle.LifecycleListener
import tech.fika.compose.multiplatform.playground.presentation.core.tools.DefaultJobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.tools.JobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageHandler
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageRelay
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
        messageRelay: MessageRelay? = null,
        jobHandler: JobHandler = DefaultJobHandler(),
        builder: StateMachineBuilder<A, E, S>.() -> Unit,
    ) : this(
        StateMachineBuilder<A, E, S>(
            jobHandler = jobHandler,
            messageRelay = messageRelay,
        ).apply(builder).build().rootNode
    )

    internal val stateMap: Map<Matcher<S, S>, StateNode<A, E, S>> = rootNode.stateMap
    internal val lifecycleNode = rootNode.lifecycleNode
    internal val interceptors = rootNode.interceptorNode.interceptors
    internal val jobHandler = rootNode.jobHandler

    val initialState: S? get() = rootNode.initialState
    val messageRelay get() = rootNode.messageRelay
    val stateListener: StateListener<A, S> get() = StateMachineStateListener(stateMachine = this)
    val lifecycleListener: LifecycleListener<A, S> get() = StateMachineLifecycleListener(stateMachine = this)
    val messageHandler: MessageHandler<A, S> get() = StateMachineMessageHandler(stateMachine = this)
    val processor: Processor<A, E, S> get() = StateMachineProcessor(stateMachine = this)
}
