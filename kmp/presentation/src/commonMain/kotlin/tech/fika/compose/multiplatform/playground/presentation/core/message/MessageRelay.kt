package tech.fika.compose.multiplatform.playground.presentation.core.message

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

interface MessageRelay : MessagePublisher, MessageSubscriber

class DefaultMessageRelay(
    override val coroutineContext: CoroutineContext = Dispatchers.Default,
) : MessageRelay, CoroutineScope {
    private val messages = MutableSharedFlow<Message>(replay = Int.MAX_VALUE, extraBufferCapacity = Int.MAX_VALUE)

    override fun publish(message: Message) {
        launch {
            messages.emit(message)
        }
    }

    override fun subscribe(onMessage: (Message) -> Unit) {
        launch {
            messages.collect(onMessage)
        }
    }

    override fun dispose() = cancel()
}

data class TestMessage(val value: String) : Message
data object Test2Message : Message
