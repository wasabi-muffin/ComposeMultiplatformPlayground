package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageRelay
import tech.fika.compose.multiplatform.playground.presentation.core.tools.JobHandler
import tech.fika.compose.multiplatform.playground.presentation.statemachine.builders.Matcher

data class RootNode<A : Action, E : Event, S : State>(
    internal val initialState: S?,
    internal val stateMap: Map<Matcher<S, S>, StateNode<A, E, S>>,
    internal val lifecycleNode: LifecycleNode<A, S>,
    internal val interceptors: List<Interceptor<A, E, S>>,
    internal val jobHandler: JobHandler,
    internal val messageRelay: MessageRelay?,
)
