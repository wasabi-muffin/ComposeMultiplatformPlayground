package tech.fika.compose.multiplatform.playground.presentation.logging

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Message
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

class LoggingInterceptor<A : Action, E : Event, S : State>(
    private val logger: Logger = DefaultLogger(),
    private val tag: String = "Macaron",
    private val level: Logger.Level = Logger.Level.Debug,
) : Interceptor<A, E, S> {
    override fun interceptAction(action: A) {
        logger.log(level = level, tag = tag) { "Action: $action" }
    }

    override fun interceptEvent(event: E) {
        logger.log(level = level, tag = tag) { "Event: $event" }
    }

    override fun interceptState(state: S) {
        logger.log(level = level, tag = tag) { "State: $state" }
    }

    override fun interceptMessage(message: Message) {
        logger.log(level = level, tag = tag) { "Message: $message" }
    }
}
