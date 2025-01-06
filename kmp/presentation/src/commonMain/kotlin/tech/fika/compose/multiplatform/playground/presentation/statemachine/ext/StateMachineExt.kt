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

fun <A : Action, E : Event, S : State> stateMachine(builder: StateMachineBuilder<A, E, S>.() -> Unit) = builder

fun <A : Action, E : Event, S : State> StateMachine<A, E, S>.store(
    initialState: S? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate),
    storeConfiguration: StoreConfiguration.Builder<A, E, S>.() -> Unit = {},
): Store<A, E, S> = DefaultStore(
    initialState = initialState ?: this.initialState ?: error("Initial State is not set"),
    processor = processor,
    coroutineScope = coroutineScope,
) {
    add(stateListener = stateListener)
    add(lifecycleListener = lifecycleListener)
    add(messageHandler = messageHandler)
    add(messageRelay = messageRelay)
    add(interceptors = interceptors)
    storeConfiguration()
}
