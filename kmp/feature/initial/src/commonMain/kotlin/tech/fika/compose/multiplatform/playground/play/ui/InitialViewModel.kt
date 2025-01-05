package tech.fika.compose.multiplatform.playground.play.ui

import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel
import tech.fika.compose.multiplatform.playground.play.presentation.InitialAction
import tech.fika.compose.multiplatform.playground.play.presentation.InitialEvent
import tech.fika.compose.multiplatform.playground.play.presentation.InitialState
import tech.fika.compose.multiplatform.playground.play.presentation.InitialStateMachine
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.store

@KoinViewModel
class InitialViewModel(
    initialStateMachine: InitialStateMachine,
) : ViewModel() {
    val store: Store<InitialAction, InitialEvent, InitialState> = initialStateMachine.store(
        initialState = InitialState.Initial(text = ""),
    )
}