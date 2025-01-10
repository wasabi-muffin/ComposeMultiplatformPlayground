package tech.fika.compose.multiplatform.playground.presentation.logging

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Transition
import tech.fika.compose.multiplatform.playground.presentation.core.message.Message
import tech.fika.compose.multiplatform.playground.presentation.logging.Logger.Level

class LoggingInterceptor<A : Action, E : Event, S : State>(
    private val tag: String,
    private val logger: Logger = DefaultLogger(),
    private val level: Level = Level.Debug,
) : Interceptor<A, E, S> {
    override fun interceptAction(action: A) {
        logger.log(level = level, tag = tag, prefix = "Action", value = action)
    }

    override fun interceptEvent(event: E) {
        logger.log(level = level, tag = tag, prefix = "Event", value = event)
    }

    override fun interceptTransition(transition: Transition<A, S, S>) {
        logger.log(level = level, tag = tag, prefix = "Transition", value = transition)
    }

    override fun interceptState(state: S) {
        logger.log(level = level, tag = tag, prefix = "State", value = state)

    }

    override fun interceptMessage(message: Message) {
        logger.log(level = level, tag = tag, prefix = "Message", value = message)
    }

    private fun Logger.log(prefix: String, value: Any, level: Level, tag: String, length: Int = 500, padding: Int = 12) {
        return value.toString().chunked(size = length).forEachIndexed { index, line ->
            log(level = level, tag = tag) {
                if (index == 0) {
                    "${"[$prefix]".padEnd(padding)} $line"
                } else {
                    "${" ".repeat(padding)} $line"
                }
            }
        }
    }
}
