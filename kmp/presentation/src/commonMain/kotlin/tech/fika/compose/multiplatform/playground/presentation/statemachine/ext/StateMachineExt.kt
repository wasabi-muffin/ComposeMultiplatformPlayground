package tech.fika.compose.multiplatform.playground.presentation.statemachine.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import tech.fika.compose.multiplatform.playground.presentation.core.components.Processor
import tech.fika.compose.multiplatform.playground.presentation.core.components.StateListener
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.lifecycle.LifecycleListener
import tech.fika.compose.multiplatform.playground.presentation.core.store.DefaultStore
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store
import tech.fika.compose.multiplatform.playground.presentation.core.store.StoreConfiguration
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageHandler
import tech.fika.compose.multiplatform.playground.presentation.statemachine.builders.StateMachineBuilder
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachineLifecycleListener
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachineMessageHandler
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachineProcessor
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachineStateListener

fun <A : Action, E : Event, S : State> stateMachine(
    builder: StateMachineBuilder<A, E, S>.() -> Unit,
) = StateMachineBuilder<A, E, S>().apply(builder).buildRoot()

val <A : Action, E : Event, S : State> StateMachine<A, E, S>.stateListener: StateListener<A, S>
    get() = StateMachineStateListener(this)

val <A : Action, E : Event, S : State> StateMachine<A, E, S>.lifecycleListener: LifecycleListener<A, S>
    get() = StateMachineLifecycleListener(this)

val <A : Action, E : Event, S : State> StateMachine<A, E, S>.messageHandler: MessageHandler<A, S>
    get() = StateMachineMessageHandler(this)

val <A : Action, E : Event, S : State> StateMachine<A, E, S>.processor: Processor<A, E, S>
    get() = StateMachineProcessor(stateMachine = this)

fun <A : Action, E : Event, S : State> StateMachine<A, E, S>.store(
    initialState: S? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate),
    storeConfiguration: StoreConfiguration.Builder<A, E, S>.() -> Unit = {},
): Store<A, E, S> = DefaultStore(
    initialState = initialState ?: this.initialState,
    processor = processor,
    coroutineScope = coroutineScope,
) {
    add(stateListener = stateListener)
    add(lifecycleListener = lifecycleListener)
    add(messageHandler = messageHandler)
    add(messageManager = messageManager)
    add(interceptors = interceptors)
    storeConfiguration()
}
