package tech.fika.compose.multiplatform.playground.presentation.core.tools

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Message
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import kotlin.coroutines.CoroutineContext

interface MessageManager : MessageSender, MessageSubscriber

fun interface MessageSender {
    fun send(message: Message)
}

interface MessageSubscriber {
    fun subscribe(onMessage: (Message) -> Unit)
    fun dispose()
}

fun interface MessageHandler<A : Action, S : State> {
    fun handle(message: Message, state: S, dispatch: (A) -> Unit)
}

class DefaultMessageManager(
    override val coroutineContext: CoroutineContext = Dispatchers.Default,
) : MessageManager, CoroutineScope {
    private val messages = MutableSharedFlow<Message>(replay = Int.MAX_VALUE, extraBufferCapacity = Int.MAX_VALUE)

    override fun send(message: Message) {
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
