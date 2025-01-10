package tech.fika.compose.multiplatform.playground.play.presentation

import org.koin.core.annotation.Factory
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageRelay
import tech.fika.compose.multiplatform.playground.presentation.logging.LoggingInterceptor
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine

@Factory(binds = [InitialStateMachine::class])
class InitialStateMachine(
    relay: MessageRelay,
) : StateMachine<InitialAction, InitialEvent, InitialState>({
    config {
        initialState = InitialState.Initial(text = "")
        messageRelay = relay
        interceptors.add(LoggingInterceptor(tag = "Initial"))
    }

    state<InitialState.Initial> {
        process<InitialAction.ClickNext> {
            send(InitialEvent.NavigateSetup(text = state.text))
        }
        process<InitialAction.InputText> {
            transition {
                state.copy(text = action.text)
            }
        }
    }
})
