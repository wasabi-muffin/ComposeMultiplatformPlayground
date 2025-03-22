package tech.fika.compose.multiplatform.playground.play.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import tech.fika.compose.multiplatform.playground.play.presentation.InitialAction
import tech.fika.compose.multiplatform.playground.play.presentation.InitialEvent
import tech.fika.compose.multiplatform.playground.play.presentation.InitialState
import tech.fika.compose.multiplatform.playground.presentation.core.store.Store
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.handleEvents
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.render
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.setLifecycleObserver
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.toViewStore

@Composable
fun InitialScreen(
    viewModel: InitialViewModel,
    router: InitialRouter,
) {
    InitialScreen(store = viewModel.store, router = router)
}

@Composable
fun InitialScreen(
    store: Store<InitialAction, InitialEvent, InitialState>,
    router: InitialRouter,
) {
    val (state, dispatch) = store.apply {
//        setLifecycleObserver()
        handleEvents {
            when (it) {
                is InitialEvent.NavigateSetup -> router.setup(name = it.text)
            }
        }
    }.toViewStore()

    InitialScreenContent(state = state, dispatch = dispatch)
}

@Composable
private fun InitialScreenContent(
    state: InitialState,
    dispatch: (InitialAction) -> Unit,
) {
    state.render<InitialState.Initial> {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = text,
                onValueChange = { dispatch(InitialAction.InputText(text = it)) },
                modifier = Modifier.fillMaxWidth().padding(24.dp)
            )
            Button(onClick = { dispatch(InitialAction.ClickNext) }) {
                Text("Next!")
            }
        }
    }
}