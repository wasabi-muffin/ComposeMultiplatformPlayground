package tech.fika.compose.multiplatform.playground.presentation.core.message

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Message

fun interface MessagePublisher {
    fun publish(message: Message)
}
