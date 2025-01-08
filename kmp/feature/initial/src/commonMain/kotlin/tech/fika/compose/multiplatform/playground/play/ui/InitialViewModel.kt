package tech.fika.compose.multiplatform.playground.play.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import tech.fika.compose.multiplatform.playground.play.presentation.InitialAction
import tech.fika.compose.multiplatform.playground.play.presentation.InitialEvent
import tech.fika.compose.multiplatform.playground.play.presentation.InitialState
import tech.fika.compose.multiplatform.playground.play.presentation.InitialStateMachine
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store
import tech.fika.compose.multiplatform.playground.presentation.logging.LoggingInterceptor
import tech.fika.compose.multiplatform.playground.presentation.saveState.SaveStateInterceptor
import tech.fika.compose.multiplatform.playground.presentation.saveState.getState
import tech.fika.compose.multiplatform.playground.presentation.saveState.setState
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.store

@KoinViewModel
class InitialViewModel(
    initialStateMachine: InitialStateMachine,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val store: Store<InitialAction, InitialEvent, InitialState> = initialStateMachine.store(
        initialState = savedStateHandle.getState(),
    ) {
        add(LoggingInterceptor(tag = "Initial"))
        add(SaveStateInterceptor(savedStateHandle::setState))
    }
}
