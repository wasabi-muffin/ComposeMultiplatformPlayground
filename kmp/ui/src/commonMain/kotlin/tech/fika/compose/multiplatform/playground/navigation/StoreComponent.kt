package tech.fika.compose.multiplatform.playground.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.KSerializer
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store

interface StoreComponent<A : Action, E : Event, S : State, N> : ComponentContext {
    val name: String
    val store: Store<A, E, S>
    val router: N

    @Composable
    fun screen()
}

interface SaveableStoreComponent<A : Action, E : Event, S : State, N> : StoreComponent<A, E, S, N> {
    val stateSerializer: KSerializer<S>

    fun getState(): S? = stateKeeper.consume(key = name, strategy = stateSerializer)
    fun saveState(state: S) {
        if (stateKeeper.isRegistered(key = name)) stateKeeper.unregister(key = name)
        stateKeeper.register(key = name, strategy = stateSerializer, supplier = { state })
    }
}

typealias AnyStoreComponent = StoreComponent<*, *, *, *>
