package tech.fika.compose.multiplatform.playground.presentation.statemachine.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.CoroutineScope
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.store.StoreConfiguration
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine

@Immutable
data class Contract<A : Action, E : Event, S : State>(
    val state: S,
    val dispatch: (A) -> Unit = {},
    internal val events: E? = null,
    internal val process: (E) -> Unit = {},
)

@Composable
fun <A : Action, E : Event, S : State> contract(
    stateMachine: StateMachine<A, E, S>,
    initialState: S? = null,
    storeConfiguration: StoreConfiguration.Builder<A, E, S>.() -> Unit = {},
): Contract<A, E, S> {
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
    val store = remember {
        stateMachine.store(
            initialState = initialState,
            lifecycle = lifecycle,
            storeConfiguration = storeConfiguration
        )
    }
    val state by store.state.collectAsState()
    val events by store.event.collectAsState(initial = null)

    DisposableEffect(Unit) {
        onDispose {
            store.dispose()
        }
    }

    return Contract(
        state = state,
        events = events,
        dispatch = { store.dispatch(it) },
        process = { store.process(it) }
    )
}

@Composable
fun <E : Event> E?.handle(process: (E) -> Unit, block: CoroutineScope.(E) -> Unit) {
    this?.let {
        LaunchedEffect(it) {
            block(it)
            process(it)
        }
    }
}

@Composable
fun <E : Event> Contract<*, E, *>.handleEvents(block: CoroutineScope.(E) -> Unit) {
    events?.handle(process = process, block = block)
}

