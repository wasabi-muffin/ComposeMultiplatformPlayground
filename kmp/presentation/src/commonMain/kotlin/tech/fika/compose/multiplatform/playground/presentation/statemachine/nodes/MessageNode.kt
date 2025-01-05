package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Message
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

data class MessageNode<A : Action, S : State, M : Message>(
    val message: M,
    val state: S,
    val dispatch: (A) -> Unit,
)

data class TestMessage(val value: String) : Message
