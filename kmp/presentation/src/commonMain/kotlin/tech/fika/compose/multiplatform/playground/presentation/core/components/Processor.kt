package tech.fika.compose.multiplatform.playground.presentation.core.components

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Transition

fun interface Processor<A : Action, E : Event, S : State> {
    suspend fun process(action: A, state: S, dispatch: (A) -> Unit, send: (E) -> Unit): Transition<A, S, S>
}
