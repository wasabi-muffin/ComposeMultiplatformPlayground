package tech.fika.compose.multiplatform.playground.presentation.statemachine.builders

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.message.Message
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Transition
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.ActionNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.ConfigNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.LifecycleNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.MessageNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.RootNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.StateListenerNode
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.StateNode

@StateMachineDsl
class StateMachineBuilder<A : Action, E : Event, S : State> {
    private val stateMap = LinkedHashMap<Matcher<S, S>, StateNode<A, E, S>>()
    private var configNode: ConfigNode<A, E, S> = ConfigNode()
    private var lifecycleNode: LifecycleNode<A, S> = LifecycleNode()

    fun <S1 : S> state(
        stateMatcher: Matcher<S, S1>,
        builder: @StateMachineDsl StateBuilder<S1>.() -> Unit,
    ) {
        stateMap[stateMatcher] = StateBuilder<S1>().apply(builder).build()
    }

    inline fun <reified S1 : S> state(
        noinline builder: @StateMachineDsl StateBuilder<S1>.() -> Unit,
    ) = state(Matcher.any(), builder)

    fun lifecycle(builder: @StateMachineDsl LifecycleBuilder<A, S>.() -> Unit) {
        lifecycleNode = LifecycleBuilder<A, S>().apply(builder).build()
    }

    fun config(builder: @StateMachineDsl ConfigBuilder<A, E, S>.() -> Unit) {
        configNode = ConfigBuilder<A, E, S>().apply(builder).build()
    }

    internal fun build(): StateMachine<A, E, S> = StateMachine(
        rootNode = RootNode(
            stateMap = stateMap.toList().reversed().toMap(),
            lifecycleNode = lifecycleNode,
            initialState = configNode.initialState,
            interceptors = configNode.interceptors,
            jobHandler = configNode.jobHandler,
            messageRelay = configNode.messageRelay,
        )
    )

    @Suppress("UNCHECKED_CAST")
    @StateDsl
    inner class StateBuilder<S1 : S> {
        private val actionMap = mutableMapOf<Matcher<A, A>, (ActionNode<A, E, S, A, S>) -> Transition<A, S, S>>()
        private val messageMap = mutableMapOf<Matcher<Message, Message>, (MessageNode<A, S, Message>) -> Unit>()
        private var stateListenerNode: StateListenerNode<A, S> = StateListenerNode()

        fun listener(builder: @StateDsl StateListenerBuilder<A, S1>.() -> Unit) {
            stateListenerNode = StateListenerBuilder<A, S1>().apply(builder).build() as StateListenerNode<A, S>
        }

        fun <A1 : A> process(
            actionMatcher: Matcher<A, A1>,
            process: @StateDsl ActionNode<A, E, S, A1, S1>.() -> Transition<A, S1, S>,
        ) {
            actionMap[actionMatcher] = { (action, currentState, dispatch, send, publish) ->
                process(
                    ActionNode<A, E, S, A1, S>(
                        action = action as A1,
                        state = currentState,
                        dispatch = dispatch,
                        send = send,
                        publish = publish,
                        jobHandler = configNode.jobHandler,
                    ) as ActionNode<A, E, S, A1, S1>
                )
            }
        }

        inline fun <reified A1 : A> process(
            noinline action: @StateDsl ActionNode<A, E, S, A1, S1>.() -> Transition<A, S1, S>,
        ) = process(Matcher.any(), action)

        fun <M : Message> receive(
            messageMatcher: Matcher<Message, M>,
            receive: @StateDsl MessageNode<A, S, M>.() -> Unit,
        ) {
            messageMap[messageMatcher] = { (message, state, dispatch) ->
                receive(
                    MessageNode(
                        message = message as M,
                        state = state,
                        dispatch = dispatch,
                    )
                )
            }
        }

        inline fun <reified M : Message> receive(
            noinline message: @StateDsl MessageNode<A, S, M>.() -> Unit,
        ) = receive(Matcher.any(), message)

        internal fun build(): StateNode<A, E, S> = StateNode(
            actionMap = actionMap,
            messageMap = messageMap,
            stateListenerNode = stateListenerNode
        )
    }
}
