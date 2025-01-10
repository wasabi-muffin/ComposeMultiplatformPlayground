package tech.fika.compose.multiplatform.playground.presentation.core.message

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

@Suppress("unused")
class MessageInterceptor<A : Action, E : Event, S : State>(
    private val messagePublisher: MessagePublisher,
    private val publish: (A) -> Message?,
) : Interceptor<A, E, S> {
    override fun interceptAction(action: A) {
        publish(action)?.let { message ->
            messagePublisher.publish(message)
        }
    }
}
