package tech.fika.compose.multiplatform.playground.presentation.core.store

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.components.LifecycleListener

interface Store<A : Action, E : Event, S : State> {
    /**
     * Stream of [State] exposed to the client
     */
    val state: StateFlow<S>

    /**
     * Stream of most recent [Event] exposed to the client
     */
    val event: Flow<E?>

    /**
     * Current [State] of the store
     */
    val currentState: S

    val lifecycleListener: LifecycleListener<A, S>?

    /**
     * Dispatches an [Action]
     */
    fun dispatch(action: A)

    /**
     * Processes an [Event]
     */
    fun process(event: E)

    /**
     * Cancels all jobs within the store
     */
    fun dispose()

    /**
     * Collect used for Kotlin Native
     */
    fun collect(
        onState: (S) -> Unit,
        onEvent: (E?) -> Unit,
    ): Job
}
