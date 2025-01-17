package tech.fika.compose.multiplatform.playground.presentation.core.store

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.components.JobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.components.LifecycleListener
import tech.fika.compose.multiplatform.playground.presentation.core.components.StateListener
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
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
        private var jobHandler: JobHandler? = null
        private var stateListener: StateListener<A, S>? = null
        private var lifecycleListener: LifecycleListener<A, S>? = null
        private var messageRelay: MessageRelay? = null
        private var messageHandler: MessageHandler<A, S>? = null
        private var interceptors: MutableList<Interceptor<A, E, S>> = mutableListOf()

        fun set(stateListener: StateListener<A, S>) {
            this.stateListener = stateListener
        }

        fun set(lifecycleListener: LifecycleListener<A, S>) {
            this.lifecycleListener = lifecycleListener
        }

        fun set(jobHandler: JobHandler) {
            this.jobHandler = jobHandler
        }

        fun set(messageRelay: MessageRelay?) {
            this.messageRelay = messageRelay
        }

        fun set(messageHandler: MessageHandler<A, S>?) {
            this.messageHandler = messageHandler
        }

        fun add(interceptor: Interceptor<A, E, S>) {
            this.interceptors.add(interceptor)
        }

        fun add(vararg interceptors: Interceptor<A, E, S>) {
            this.interceptors.addAll(interceptors)
        }

        fun add(interceptors: List<Interceptor<A, E, S>>) = add(*interceptors.toTypedArray())

        fun build(): StoreConfiguration<A, E, S> = StoreConfiguration(
            jobHandler = jobHandler ?: JobHandler.default(),
            stateListener = stateListener,
            lifecycleListener = lifecycleListener,
            messageRelay = messageRelay,
            messageHandler = messageHandler,
            interceptors = interceptors,
        )
    }
}