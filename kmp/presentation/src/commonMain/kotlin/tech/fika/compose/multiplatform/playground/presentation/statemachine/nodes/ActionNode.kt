package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import kotlinx.coroutines.CoroutineScope
import tech.fika.compose.multiplatform.playground.presentation.core.components.JobHandler
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Transition
import tech.fika.compose.multiplatform.playground.presentation.core.message.Message

@Suppress("unused")
data class ActionNode<A : Action, E : Event, S : State, A1 : A, S1 : S>(
    val action: A1,
    val state: S1,
    val dispatch: (A) -> Transition.Empty,
    val send: (E) -> Transition.Empty,
    val publish: (Message) -> Transition.Empty,
    val jobHandler: JobHandler,
) {
    fun emptyTransition() = Transition.Empty

    fun invalidTransition() = Transition.Invalid

    fun <S2 : S> transition(nextState: S1.() -> S2): Transition.Valid<A, S1, S2> =
        Transition.Valid(action = action, currentState = state, nextState = state.nextState())

    fun launch(key: Any? = null, block: suspend CoroutineScope.() -> Unit): Transition.Empty {
        jobHandler.launch(key = key, block = block)
        return Transition.Empty
    }
}
