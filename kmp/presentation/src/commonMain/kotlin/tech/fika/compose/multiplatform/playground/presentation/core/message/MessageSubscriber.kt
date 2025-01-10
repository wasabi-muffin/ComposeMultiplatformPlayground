package tech.fika.compose.multiplatform.playground.presentation.core.message

interface MessageSubscriber {
    fun subscribe(onMessage: (Message) -> Unit)
    fun dispose()
}
