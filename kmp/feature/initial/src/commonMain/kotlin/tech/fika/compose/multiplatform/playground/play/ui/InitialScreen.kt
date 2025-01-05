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
import org.koin.compose.koinInject
import tech.fika.compose.multiplatform.playground.play.presentation.InitialAction
import tech.fika.compose.multiplatform.playground.play.presentation.InitialEvent
import tech.fika.compose.multiplatform.playground.play.presentation.InitialState
import tech.fika.compose.multiplatform.playground.play.presentation.InitialStateMachine
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageManager
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.contract
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.handleEvents

@Composable
fun InitialScreen(
    stateMachine: InitialStateMachine,
    route: InitialRoute,
    navigator: InitialNavigator,
) {
    val (state, dispatch) = contract(stateMachine).handleEvents {
        when (it) {
            is InitialEvent.NavigateSetup -> navigator.setup(name = it.text)
        }
    }

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