package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import kotlinx.coroutines.CoroutineScope
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Transition
import tech.fika.compose.multiplatform.playground.presentation.core.tools.JobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.tools.Message
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageSender

data class ActionNode<A : Action, E : Event, S : State, A1 : A, S1 : S>(
    val action: A1,
    val state: S1,
    internal val dispatchAction: (A) -> Unit,
    internal val sendEvent: (E) -> Unit,
    private val jobHandler: JobHandler,
    private val messageSender: MessageSender?,
) {
    fun emptyTransition() = Transition.Empty
    fun invalidTransition() = Transition.Invalid
    fun <S2 : S> transition(nextState: S1.() -> S2): Transition.Valid<A, S1, S2> =
        Transition.Valid(action = action, currentState = state, nextState = state.nextState())

    fun dispatch(action: A): Transition.Empty {
        dispatchAction(action)
        return Transition.Empty
    }

    fun launch(
        key: String? = null,
        coroutineScope: CoroutineScope = jobHandler,
        block: suspend CoroutineScope.() -> Unit,
    ): Transition.Empty {
        jobHandler.launch(key, coroutineScope, block)
        return Transition.Empty
    }

    fun send(event: E): Transition.Empty {
        sendEvent(event)
        return Transition.Empty
    }

    fun send(message: Message): Transition.Empty {
        messageSender?.send(message)
        return Transition.Empty
    }
}

