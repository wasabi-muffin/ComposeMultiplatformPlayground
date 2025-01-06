package tech.fika.compose.multiplatform.playground.presentation.core.store

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.components.StateListener
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.lifecycle.LifecycleListener
import tech.fika.compose.multiplatform.playground.presentation.core.tools.DefaultJobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.tools.JobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageHandler
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageRelay

data class StoreConfiguration<A : Action, E : Event, S : State>(
    val jobHandler: JobHandler,
    val stateListener: StateListener<A, S>?,
    val lifecycleListener: LifecycleListener<A, S>?,
    val messageRelay: MessageRelay?,
    val messageHandler: MessageHandler<A, S>?,
    val interceptors: List<Interceptor<A, E, S>>,
) {
    class Builder<A : Action, E : Event, S : State> {
        private var jobHandler: JobHandler = DefaultJobHandler()
        private var stateListener: StateListener<A, S>? = null
        private var lifecycleListener: LifecycleListener<A, S>? = null
        private var messageRelay: MessageRelay? = null
        private var messageHandler: MessageHandler<A, S>? = null
        private var interceptors: MutableList<Interceptor<A, E, S>> = mutableListOf()

        fun add(stateListener: StateListener<A, S>) {
            this.stateListener = stateListener
        }

        fun add(lifecycleListener: LifecycleListener<A, S>) {
            this.lifecycleListener = lifecycleListener
        }

        fun add(jobHandler: JobHandler) {
            this.jobHandler = jobHandler
        }

        fun add(messageRelay: MessageRelay?) {
            this.messageRelay = messageRelay
        }

        fun add(messageHandler: MessageHandler<A, S>?) {
            this.messageHandler = messageHandler
        }

        fun add(vararg interceptors: Interceptor<A, E, S>) {
            this.interceptors.addAll(interceptors)
        }

        fun add(interceptors: List<Interceptor<A, E, S>>) = add(*interceptors.toTypedArray())

        fun build(): StoreConfiguration<A, E, S> = StoreConfiguration(
            jobHandler = jobHandler,
            stateListener = stateListener,
            lifecycleListener = lifecycleListener,
            messageRelay = messageRelay,
            messageHandler = messageHandler,
            interceptors = interceptors,
        )
    }
}