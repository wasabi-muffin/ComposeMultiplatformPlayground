package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import tech.fika.compose.multiplatform.playground.presentation.core.components.Processor
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Transition
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.ActionNode

class StateMachineProcessor<A : Action, E : Event, S : State>(
    private val stateMachine: StateMachine<A, E, S>,
) : Processor<A, E, S> {
    override suspend fun process(
        action: A,
        state: S,
        dispatch: (A) -> Unit,
        send: (E) -> Unit,
    ): Transition<A, S, S> = stateMachine.stateMap
        .filterKeys { key -> key.matches(value = state) }
        .values
        .flatMap { stateNode -> stateNode.actionMap.entries }
        .find { actionMatcher -> actionMatcher.key.matches(value = action) }
        ?.value
        ?.invoke(
            ActionNode(
                action = action,
                state = state,
                dispatchAction = dispatch,
                sendEvent = send,
                jobHandler = stateMachine.jobHandler,
                messageSender = stateMachine.messageManager,
            )
        )
        ?: Transition.Invalid
}
