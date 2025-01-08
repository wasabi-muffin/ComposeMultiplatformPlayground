package tech.fika.compose.multiplatform.playground.presentation.statemachine.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.store.DefaultStore
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store
import tech.fika.compose.multiplatform.playground.presentation.core.store.StoreConfiguration
import tech.fika.compose.multiplatform.playground.presentation.statemachine.builders.StateMachineBuilder
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachineLifecycleListener
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachineMessageHandler
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachineProcessor
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachineStateListener

fun <A : Action, E : Event, S : State> stateMachineBuilder(
    builder: StateMachineBuilder<A, E, S>.() -> Unit,
) = StateMachineBuilder<A, E, S>().apply(builder).build()

fun <A : Action, E : Event, S : State> StateMachine<A, E, S>.store(
    initialState: S? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate),
    storeConfiguration: StoreConfiguration.Builder<A, E, S>.() -> Unit = {},
): Store<A, E, S> = DefaultStore(
    initialState = initialState ?: rootNode.configNode.initialState ?: error("Initial State is not set"),
    processor = StateMachineProcessor(rootNode = rootNode),
    coroutineScope = coroutineScope,
) {
    add(stateListener = StateMachineStateListener(rootNode = rootNode))
    add(lifecycleListener = StateMachineLifecycleListener(rootNode = rootNode))
    add(messageHandler = StateMachineMessageHandler(rootNode = rootNode))
    add(messageRelay = rootNode.configNode.messageRelay)
    add(interceptors = rootNode.configNode.interceptors)
    storeConfiguration()
}

inline fun <reified S : State> State.on(block: S.() -> Unit) {
    if (this is S) {
        block(this)
    }
}