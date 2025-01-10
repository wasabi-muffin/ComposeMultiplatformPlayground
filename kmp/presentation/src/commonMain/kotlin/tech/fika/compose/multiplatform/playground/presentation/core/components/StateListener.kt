package tech.fika.compose.multiplatform.playground.presentation.core.components

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

interface StateListener<A : Action, S : State> {
    fun onEnter(state: S, dispatch: (A) -> Unit) = Unit
    fun onRepeat(state: S, dispatch: (A) -> Unit) = Unit
    fun onExit(state: S, dispatch: (A) -> Unit) = Unit

    companion object {
        @Suppress("unused")
        fun <A : Action, S : State> default() = EmptyStateListener<A, S>()
    }
}

class EmptyStateListener<A : Action, S : State> : StateListener<A, S>
