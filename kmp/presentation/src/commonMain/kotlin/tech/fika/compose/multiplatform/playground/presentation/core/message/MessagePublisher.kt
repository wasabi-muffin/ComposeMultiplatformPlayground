package tech.fika.compose.multiplatform.playground.presentation.core.message

fun interface MessagePublisher {
    fun publish(message: Message)
}
