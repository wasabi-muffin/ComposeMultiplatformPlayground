package tech.fika.compose.multiplatform.playground.play.presentation

import org.koin.core.annotation.Factory
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageManager
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.TestMessage

@Factory(binds = [InitialStateMachine::class])
class InitialStateMachine(
    messageManager: MessageManager,
) : StateMachine<InitialAction, InitialEvent, InitialState>(
    messageManager = messageManager,
    builder = {
        initialState { InitialState.Initial(text = "") }

        state<InitialState> {
            receive<TestMessage> {
                dispatch(InitialAction.InputText(text = message.value))
            }
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
