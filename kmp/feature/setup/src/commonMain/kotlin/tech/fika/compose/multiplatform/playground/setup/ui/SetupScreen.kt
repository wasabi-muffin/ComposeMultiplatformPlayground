package tech.fika.compose.multiplatform.playground.setup.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import composemultiplatformplayground.kmp.feature.setup.generated.resources.Res
import composemultiplatformplayground.kmp.feature.setup.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageManager
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.store
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupAction
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupState
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupStateMachine

@Composable
fun SetupScreen() {
    val stateMachine = koinInject<SetupStateMachine>()
    val messageManager = koinInject<MessageManager>()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val store = remember {
        stateMachine.store(
            initialState = SetupState.Initial,
            lifecycle = lifecycle
        ) {
            add(messageManager)
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            store.dispose()
        }
    }
    val state = store.state.collectAsState()
    val dispatch = store::dispatch

    SetupScreenContent(state = state.value, dispatch = dispatch)
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