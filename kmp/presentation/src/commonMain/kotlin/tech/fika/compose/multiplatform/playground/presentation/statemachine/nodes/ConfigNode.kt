package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.components.JobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageRelay

data class ConfigNode<A : Action, E : Event, S : State>(
    val initialState: S? = null,
    val messageRelay: MessageRelay? = null,
    val jobHandler: JobHandler = JobHandler.default(),
    val interceptors: List<Interceptor<A, E, S>> = emptyList(),
)