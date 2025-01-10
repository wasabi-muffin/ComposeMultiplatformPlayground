package tech.fika.compose.multiplatform.playground.presentation.stateSaver

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

class StateSaverInterceptor<A : Action, E : Event, S : State>(
    private val saveState: (S) -> Unit,
) : Interceptor<A, E, S> {
    override fun interceptState(state: S) = saveState(state)
}
