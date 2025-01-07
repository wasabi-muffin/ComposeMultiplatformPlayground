package tech.fika.compose.multiplatform.playground.presentation.core.message

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

fun interface MessageHandler<A : Action, S : State> {
    fun handle(message: Message, state: S, dispatch: (A) -> Unit): Unit?
}