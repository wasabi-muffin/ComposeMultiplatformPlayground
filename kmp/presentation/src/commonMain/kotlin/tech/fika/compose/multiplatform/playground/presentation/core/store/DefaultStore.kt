package tech.fika.compose.multiplatform.playground.presentation.core.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import tech.fika.compose.multiplatform.playground.presentation.core.components.Processor
import tech.fika.compose.multiplatform.playground.presentation.core.components.StateListener
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Transition.Valid

class DefaultStore<A : Action, E : Event, S : State>(
    initialState: S,
    private val processor: Processor<A, E, S>,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate),
    storeConfiguration: StoreConfiguration.Builder<A, E, S>.() -> Unit,
) : Store<A, E, S> {
    private val mutex: Mutex = Mutex()
    private val configuration = StoreConfiguration.Builder<A, E, S>().apply(storeConfiguration).build()
    private val stateFlow = MutableStateFlow(initialState)
    private val eventFlow = MutableStateFlow<E?>(null)

    override val state = stateFlow
    override val currentState: S get() = stateFlow.value
    override val event get() = eventFlow
    override val lifecycleListener = configuration.lifecycleListener

    init {
        configuration.stateListener?.onEnter(state = initialState, dispatch = ::dispatch)
        configuration.interceptors.forEach { it.interceptState(initialState) }
        configuration.messageRelay?.subscribe { message ->
            configuration.messageHandler?.handle(
                message = message,
                state = currentState,
                dispatch = ::dispatch
            )?.let {
                configuration.interceptors.forEach { it.interceptMessage(message) }
            }
        }
    }

    override fun dispatch(action: A) {
        coroutineScope.launch {
            mutex.withLock {
                configuration.interceptors.forEach { it.interceptAction(action) }
                val transition = processor.process(
                    action = action,
                    state = currentState,
                    send = ::send,
                    dispatch = ::dispatch
                )
                configuration.interceptors.forEach { it.interceptTransition(transition) }
                val nextState = if (transition is Valid<A, S, S>) {
                    configuration.stateListener?.observe(transition = transition)
                    transition.nextState
                } else {
                    currentState
                }
                configuration.interceptors.forEach { it.interceptState(nextState) }
                stateFlow.update { nextState }
            }
        }
    }

    override fun process(event: E) {
        coroutineScope.launch { eventFlow.emit(null) }
    }

    private fun send(event: E) {
        configuration.interceptors.forEach { it.interceptEvent(event) }
        coroutineScope.launch { eventFlow.emit(value = event) }
    }

    override fun dispose() {
        configuration.jobHandler.cancelAll()
        coroutineScope.cancel()
    }

    override fun collect(
        onState: (S) -> Unit,
        onEvent: (E?) -> Unit,
    ): Job = coroutineScope.launch {
        launch { state.collect { onState(it) } }
        launch { event.collect { onEvent(it) } }
    }

    private fun StateListener<A, S>.observe(transition: Valid<A, S, S>) {
        if (transition.currentState::class != transition.nextState::class) {
            onExit(state = transition.currentState, dispatch = ::dispatch)
            onEnter(state = transition.nextState, dispatch = ::dispatch)
        } else {
            onRepeat(state = transition.nextState, dispatch = ::dispatch)
        }
    }
}
