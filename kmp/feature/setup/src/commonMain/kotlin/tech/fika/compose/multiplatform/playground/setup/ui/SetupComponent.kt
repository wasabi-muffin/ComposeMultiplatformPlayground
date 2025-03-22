package tech.fika.compose.multiplatform.playground.setup.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.KSerializer
import tech.fika.compose.multiplatform.playground.navigation.Route
import tech.fika.compose.multiplatform.playground.navigation.SaveableStoreComponent
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store
import tech.fika.compose.multiplatform.playground.presentation.stateSaver.StateSaverInterceptor
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.store
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupAction
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupEvent
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupState
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupStateMachine

class SetupComponent(
    componentContext: ComponentContext,
    stateMachine: SetupStateMachine,
    route: Route.Setup,
    override val router: SetupRouter,
) : SaveableStoreComponent<SetupAction, SetupEvent, SetupState, SetupRouter>, ComponentContext by componentContext {
    override val name: String = "Setup"
    override val stateSerializer: KSerializer<SetupState> = SetupState.serializer()
    override val store: Store<SetupAction, SetupEvent, SetupState> = stateMachine.store(
        initialState = SetupState.Initial(route.name)
    ) {
        add(StateSaverInterceptor(::saveState))
    }

    @Composable
    override fun screen() = SetupScreen(store = store, router = router)
}
