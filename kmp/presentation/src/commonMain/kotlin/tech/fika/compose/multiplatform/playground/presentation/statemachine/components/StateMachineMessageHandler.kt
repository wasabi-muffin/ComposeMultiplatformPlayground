package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.message.Message
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageHandler
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.MessageNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.RootNode

class StateMachineMessageHandler<A : Action, E : Event, S : State>(
    private val rootNode: RootNode<A, E, S>,
) : MessageHandler<A, S> {
    override fun handle(message: Message, state: S, dispatch: (A) -> Unit): Unit? = rootNode.stateMap
        .filterKeys { key -> key.matches(value = state) }
        .values
        .flatMap { stateNode -> stateNode.messageMap.entries }
        .find { entry -> entry.key.matches(value = message) }
        ?.value
        ?.invoke(MessageNode(message = message, state = state, dispatch = dispatch))
}
