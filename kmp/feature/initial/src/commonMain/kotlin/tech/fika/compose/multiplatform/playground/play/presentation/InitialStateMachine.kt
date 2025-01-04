package tech.fika.compose.multiplatform.playground.play.presentation

import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine

//typealias InitialStateMachine = StateMachine<InitialAction, InitialEvent, InitialState>

@Single
class InitialStateMachine : StateMachine<InitialAction, InitialEvent, InitialState>(
    {
        initialState { InitialState.Initial }

        state<InitialState.Initial> {
            process<InitialAction.ClickNext> {
                send(InitialEvent.NavigateSetup)
            }
        }
    }
)