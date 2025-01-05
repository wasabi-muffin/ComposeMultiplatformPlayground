package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Message
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageHandler
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.MessageNode

class StateMachineMessageListener<A : Action, E : Event, S : State>(
    private val stateMachine: StateMachine<A, E, S>,
) : MessageHandler<A, S> {
    override fun handle(message: Message, state: S, dispatch: (A) -> Unit) = stateMachine.stateMap
        .filterKeys { key -> key.matches(value = state) }
        .values
        .flatMap { stateNode -> stateNode.messageMap.entries }
        .find { (key, _) ->
            key.matches(value = message)
        }
        ?.value
        ?.invoke(
            MessageNode(
                message = message,
                state = state,
                dispatch = dispatch,
            )
        ) ?: Unit
}