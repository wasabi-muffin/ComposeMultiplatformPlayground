package tech.fika.compose.multiplatform.playground.presentation.core.components

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.tools.Message

interface Interceptor<A : Action, E : Event, S : State> {
    fun interceptAction(action: A) = Unit
    fun interceptEvent(event: E) = Unit
    fun interceptState(state: S) = Unit
    fun interceptMessage(message: Message) = Unit
}