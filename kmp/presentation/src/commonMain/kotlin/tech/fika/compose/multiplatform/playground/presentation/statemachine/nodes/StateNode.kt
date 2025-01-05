package tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Message
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Transition
import tech.fika.compose.multiplatform.playground.presentation.statemachine.builders.Matcher

data class StateNode<A : Action, E : Event, S : State>(
    val actionMap: Map<Matcher<A, A>, (ActionNode<A, E, S, A, S>) -> Transition<A, S, S>>,
    val messageMap: Map<Matcher<Message, Message>, (MessageNode<A, S, Message>) -> Unit>,
    val stateListenerNode: StateListenerNode<A, S>,
)