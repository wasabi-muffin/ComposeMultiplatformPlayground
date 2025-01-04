package tech.fika.compose.multiplatform.playground.presentation.core.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

interface LifecycleListener<A : Action, S : State> : DefaultLifecycleObserver {
    fun onCreate(state: S, dispatch: (A) -> Unit) = Unit
    fun onStart(state: S, dispatch: (A) -> Unit) = Unit
    fun onResume(state: S, dispatch: (A) -> Unit) = Unit
    fun onPause(state: S, dispatch: (A) -> Unit) = Unit
    fun onStop(state: S, dispatch: (A) -> Unit) = Unit
    fun onDestroy() = Unit

    fun toLifecycleObserver(getState: () -> S, dispatch: (A) -> Unit): LifecycleObserver =
        object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) = onCreate(getState(), dispatch)
            override fun onStart(owner: LifecycleOwner) = onStart(getState(), dispatch)
            override fun onResume(owner: LifecycleOwner) = onResume(getState(), dispatch)
            override fun onPause(owner: LifecycleOwner) = onPause(getState(), dispatch)
            override fun onStop(owner: LifecycleOwner) = onStop(getState(), dispatch)
            override fun onDestroy(owner: LifecycleOwner) {
                onDestroy()
            }
        }

    companion object {
        fun <A : Action, S : State> default() = object : LifecycleListener<A, S> {}
    }
}
