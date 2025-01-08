package tech.fika.compose.multiplatform.playground.presentation.core.components

import androidx.lifecycle.DefaultLifecycleObserver
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

interface LifecycleListener<A : Action, S : State> : DefaultLifecycleObserver {
    fun onCreate(state: S, dispatch: (A) -> Unit) = Unit
    fun onStart(state: S, dispatch: (A) -> Unit) = Unit
    fun onResume(state: S, dispatch: (A) -> Unit) = Unit
    fun onPause(state: S, dispatch: (A) -> Unit) = Unit
    fun onStop(state: S, dispatch: (A) -> Unit) = Unit
    fun onDestroy() = Unit

    companion object {
        fun <A : Action, S : State> default() = EmptyLifecycleListener<A, S>()
    }
}

class EmptyLifecycleListener<A : Action, S : State>: LifecycleListener<A, S>