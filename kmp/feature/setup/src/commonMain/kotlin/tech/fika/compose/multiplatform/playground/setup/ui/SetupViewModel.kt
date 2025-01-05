package tech.fika.compose.multiplatform.playground.setup.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import org.koin.android.annotation.KoinViewModel
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageManager
import tech.fika.compose.multiplatform.playground.presentation.logging.LoggingInterceptor
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.store
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupState
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupStateMachine

@KoinViewModel
class SetupViewModel(
    setupStateMachine: SetupStateMachine,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val route: SetupRoute = savedStateHandle.toRoute()
    val store = setupStateMachine.store(
        initialState = SetupState.Initial(name = route.name),
    ) {
        add(LoggingInterceptor())
    }
}
