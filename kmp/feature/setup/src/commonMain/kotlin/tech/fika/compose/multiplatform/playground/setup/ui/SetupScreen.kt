package tech.fika.compose.multiplatform.playground.setup.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import composemultiplatformplayground.kmp.feature.setup.generated.resources.Res
import composemultiplatformplayground.kmp.feature.setup.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.contract
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupAction
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupState
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupStateMachine

@Composable
fun SetupScreen() {
    val stateMachine: SetupStateMachine = koinInject()
    val contract = contract(stateMachine = stateMachine)

    SetupScreenContent(state = contract.state, dispatch = contract.dispatch)
}

@Composable
private fun SetupScreenContent(
    state: SetupState,
    dispatch: (SetupAction) -> Unit,
) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (state is SetupState.Stable) {
            Button(onClick = { dispatch(SetupAction.ClickMe) }) {
                Text("Click me!")
            }
            AnimatedVisibility(state.isShowContent) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: ${state.greeting.greet}")
                }
            }
        }
    }
}