package tech.fika.compose.multiplatform.playground.presentation.statemachine.components

import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.store.DefaultStore
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store
import tech.fika.compose.multiplatform.playground.presentation.core.store.StoreConfiguration
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageHandler
import tech.fika.compose.multiplatform.playground.presentation.statemachine.builders.StateMachineBuilder

fun <A : Action, E : Event, S : State> stateMachine(
    builder: StateMachineBuilder<A, E, S>.() -> Unit,
) = StateMachineBuilder<A, E, S>().apply(builder).build()

fun <A : Action, E : Event, S : State> StateMachine<A, E, S>.store(
    initialState: S? = null,
    lifecycle: Lifecycle? = null,
    messageHandler: MessageHandler<A>? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate),
    storeConfiguration: StoreConfiguration.Builder<A, E, S>.() -> Unit = {},
): Store<A, E, S> = DefaultStore(
    initialState = initialState ?: this.initialState,
    processor = processor,
    lifecycle = lifecycle,
    coroutineScope = coroutineScope,
) {
    +StateMachineStateListener(this@store)
    +StateMachineLifecycleListener(this@store)
    +this@store.jobHandler
    +messageManager
    +(messageHandler ?: this@store.messageHandler)
    +interceptors
    storeConfiguration()
}
