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
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.handleEvents
import tech.fika.compose.multiplatform.playground.presentation.statemachine.ext.toViewStore
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupAction
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupEvent
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupState

@Composable
fun SetupScreen(
    viewModel: SetupViewModel,
    navigator: SetupNavigator,
) {
    val (state, dispatch) = viewModel.store.apply {
        handleEvents { event ->
            when (event) {
                SetupEvent.NavigateBack -> navigator.back()
            }
        }
    }.toViewStore()

    SetupScreenContent(state = state, dispatch = dispatch)
}

@Composable
private fun SetupScreenContent(
    state: SetupState,
    dispatch: (SetupAction) -> Unit,
) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { dispatch(SetupAction.ClickBack) }) {
            Text("Back")
        }
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
                    Text("${state.name}: ${state.greeting.greet}")
                }
            }
        }
    }
}
