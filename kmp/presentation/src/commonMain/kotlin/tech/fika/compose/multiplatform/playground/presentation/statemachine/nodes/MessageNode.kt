package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.message.Message

data class MessageNode<A : Action, S : State, M : Message>(
    val message: M,
    val state: S,
    val dispatch: (A) -> Unit,
)
