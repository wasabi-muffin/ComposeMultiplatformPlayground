package tech.fika.compose.multiplatform.playground.play.presentation

import org.koin.core.annotation.Factory
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine

@Factory(binds = [InitialStateMachine::class])
class InitialStateMachine : StateMachine<InitialAction, InitialEvent, InitialState>(
    {
        initialState { InitialState.Initial(text = "") }

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
    }
)