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
import tech.fika.compose.multiplatform.playground.presentation.core.lifecycle.LifecycleListener
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store

@Immutable
data class Contract<A : Action, E : Event, S : State>(
    val state: S,
    val dispatch: (A) -> Unit = {},
    internal val events: E? = null,
    internal val process: (E) -> Unit = {},
)

@Composable
fun <A : Action, E : Event, S : State> Store<A, E, S>.createContract(): Contract<A, E, S> {
    val lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
    val state by state.collectAsState()
    val events by event.collectAsState(initial = null)
    val lifecycleObserver = lifecycleListener?.toLifecycleObserver(
        getState = { currentState },
        dispatch = ::dispatch
    )

    DisposableEffect(Unit) {
        lifecycleObserver?.let {
            lifecycle.addObserver(it)
        }
        onDispose {
            lifecycleObserver?.let {
                lifecycle.removeObserver(it)
            }
        }
    }

    return Contract(state = state, events = events, dispatch = ::dispatch, process = ::process)
}

fun <A : Action, S : State> LifecycleListener<A, S>.toLifecycleObserver(
    getState: () -> S,
    dispatch: (A) -> Unit,
): LifecycleObserver = object : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) = onCreate(getState(), dispatch)
    override fun onStart(owner: LifecycleOwner) = onStart(getState(), dispatch)
    override fun onResume(owner: LifecycleOwner) = onResume(getState(), dispatch)
    override fun onPause(owner: LifecycleOwner) = onPause(getState(), dispatch)
    override fun onStop(owner: LifecycleOwner) = onStop(getState(), dispatch)
    override fun onDestroy(owner: LifecycleOwner) = onDestroy()
}

@Composable
private fun <E : Event> E?.handle(process: (E) -> Unit, block: CoroutineScope.(E) -> Unit) {
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

