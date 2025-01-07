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
import tech.fika.compose.multiplatform.playground.play.presentation.InitialAction
import tech.fika.compose.multiplatform.playground.play.presentation.InitialEvent
import tech.fika.compose.multiplatform.playground.play.presentation.InitialState
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.handleEvents
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.setLifecycleObserver
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.toViewStore

@Composable
fun InitialScreen(
    viewModel: InitialViewModel,
    navigator: InitialNavigator,
) {
    val (state, dispatch) = viewModel.store
        .setLifecycleObserver()
        .handleEvents {
            when (it) {
                is InitialEvent.NavigateSetup -> navigator.setup(name = it.text)
            }
        }
        .toViewStore()

    InitialScreenContent(state = state, dispatch = dispatch)
}

@Composable
private fun InitialScreenContent(
    state: InitialState,
    dispatch: (InitialAction) -> Unit,
) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = state.text,
            onValueChange = { dispatch(InitialAction.InputText(text = it)) },
            modifier = Modifier.fillMaxWidth().padding(24.dp)
        )
        Button(onClick = { dispatch(InitialAction.ClickNext) }) {
            Text("Next!")
        }
    }
}