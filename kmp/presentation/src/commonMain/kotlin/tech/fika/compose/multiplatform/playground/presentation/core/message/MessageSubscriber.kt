package tech.fika.compose.multiplatform.playground.presentation.core.message

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Message

interface MessageSubscriber {
    fun subscribe(onMessage: (Message) -> Unit)
    fun dispose()
}