package tech.fika.compose.multiplatform.playground.play.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import tech.fika.compose.multiplatform.playground.play.presentation.InitialAction
import tech.fika.compose.multiplatform.playground.play.presentation.InitialEvent
import tech.fika.compose.multiplatform.playground.play.presentation.InitialState
import tech.fika.compose.multiplatform.playground.play.presentation.InitialStateMachine
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.contract
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.handleEvents

@Composable
fun InitialScreen() {
    val stateMachine: InitialStateMachine = koinInject()
    val contract = contract(stateMachine)

    contract.handleEvents {
        when (it) {
            InitialEvent.NavigateSetup -> println("Navigate Setup")
        }
    }

    InitialScreenContent(
        state = contract.state,
        dispatch = contract.dispatch,
    )
}

@Composable
private fun InitialScreenContent(
    state: InitialState,
    dispatch: (InitialAction) -> Unit,
) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { dispatch(InitialAction.ClickNext) }) {
            Text("Next!")
        }
    }
}