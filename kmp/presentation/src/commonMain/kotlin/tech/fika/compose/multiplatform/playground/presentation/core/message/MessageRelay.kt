package tech.fika.compose.multiplatform.playground.presentation.core.message

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

interface MessageRelay : MessagePublisher, MessageSubscriber {
    companion object {
        fun default(coroutineContext: CoroutineContext = Dispatchers.Default) = object : MessageRelay {
            private val messages = MutableSharedFlow<Message>(replay = Int.MAX_VALUE, extraBufferCapacity = Int.MAX_VALUE)
            val scope = CoroutineScope(context = coroutineContext)

            override fun publish(message: Message) {
                scope.launch {
                    messages.emit(message)
                }
            }

            override fun subscribe(onMessage: (Message) -> Unit) {
                scope.launch {
                    messages.collect(onMessage)
                }
            }

            override fun dispose() = scope.cancel()
        }
    }
}

data class TestMessage(val value: String) : Message
data object Test2Message : Message
