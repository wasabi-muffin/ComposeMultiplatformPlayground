package tech.fika.compose.multiplatform.playground.presentation.statemachine.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.CoroutineScope
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store

@Immutable
data class ViewStore<A : Action, S : State>(
    val state: S,
    val dispatch: (A) -> Unit = {},
)

inline fun <reified S : State> State.render(block: S.() -> Unit) {
    if (this is S) block()
}

@Composable
fun <A : Action, E : Event, S : State> Store<A, E, S>.toViewStore(): ViewStore<A, S> {
    val state by state.collectAsState()

    return ViewStore(state = state, dispatch = ::dispatch)
}

@Composable
fun <A : Action, E : Event, S : State> Store<A, E, S>.setLifecycleObserver() {
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle

    lifecycleObserver?.let { observer ->
        DisposableEffect(key1 = lifecycle) {
            lifecycle.addObserver(observer = observer)
            onDispose {
                lifecycle.removeObserver(observer = observer)
            }
        }
    }
}

val <A : Action, E : Event, S : State> Store<A, E, S>.lifecycleObserver: LifecycleObserver?
    get() = lifecycleListener?.let { listener ->
        object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) = listener.onCreate(currentState, ::dispatch)
            override fun onStart(owner: LifecycleOwner) = listener.onStart(currentState, ::dispatch)
            override fun onResume(owner: LifecycleOwner) = listener.onResume(currentState, ::dispatch)
            override fun onPause(owner: LifecycleOwner) = listener.onPause(currentState, ::dispatch)
            override fun onStop(owner: LifecycleOwner) = listener.onStop(currentState, ::dispatch)
            override fun onDestroy(owner: LifecycleOwner) = listener.onDestroy()
        }
    }

@Composable
fun <A : Action, E : Event, S : State> Store<A, E, S>.handleEvents(
    block: CoroutineScope.(E) -> Unit,
) {
    val events by event.collectAsState(initial = null)
    LaunchedEffect(events) {
        events?.let { event ->
            block(event)
            process(event)
        }
    }
}
