package tech.fika.compose.multiplatform.playground.presentation.statemachine.builders

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.components.JobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageRelay
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.ConfigNode

@ConfigDsl
class ConfigBuilder<A : Action, E : Event, S : State> {
    var initialState: S? = null
    var messageRelay: MessageRelay? = null
    var jobHandler: JobHandler = JobHandler.default()
    val interceptors = mutableListOf<Interceptor<A, E, S>>()

    fun set(messageRelay: MessageRelay) {
        this.messageRelay = messageRelay
    }

    fun set(jobHandler: JobHandler) {
        this.jobHandler = jobHandler
    }

    fun add(interceptor: Interceptor<A, E, S>) {
        this.interceptors.add(interceptor)
    }

    fun add(vararg interceptors: Interceptor<A, E, S>) {
        this.interceptors.addAll(interceptors)
    }

    fun build() = ConfigNode(
        initialState = initialState,
        messageRelay = messageRelay,
        jobHandler = jobHandler,
        interceptors = interceptors
    )
}