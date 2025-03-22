package tech.fika.compose.multiplatform.playground.play.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.KSerializer
import tech.fika.compose.multiplatform.playground.navigation.Route
import tech.fika.compose.multiplatform.playground.navigation.SaveableStoreComponent
import tech.fika.compose.multiplatform.playground.play.presentation.InitialAction
import tech.fika.compose.multiplatform.playground.play.presentation.InitialEvent
import tech.fika.compose.multiplatform.playground.play.presentation.InitialState
import tech.fika.compose.multiplatform.playground.play.presentation.InitialStateMachine
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store
import tech.fika.compose.multiplatform.playground.presentation.stateSaver.StateSaverInterceptor
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.store

class InitialComponent(
    componentContext: ComponentContext,
    stateMachine: InitialStateMachine,
    route: Route.Initial,
    override val router: InitialRouter,
) : SaveableStoreComponent<InitialAction, InitialEvent, InitialState, InitialRouter>, ComponentContext by componentContext {
    override val name: String = "Initial"
    override val stateSerializer: KSerializer<InitialState> = InitialState.serializer()
    override val store: Store<InitialAction, InitialEvent, InitialState> = stateMachine.store(
        initialState = InitialState.Initial(text = route.name)
    ) {
        add(StateSaverInterceptor(::saveState))
    }

    @Composable
    override fun screen() = InitialScreen(store, router)
}