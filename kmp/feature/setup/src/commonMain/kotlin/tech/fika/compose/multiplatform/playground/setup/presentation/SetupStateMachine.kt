package tech.fika.compose.multiplatform.playground.setup.presentation

import org.koin.core.annotation.Factory
import tech.fika.compose.multiplatform.playground.domain.core.ErrorHandler
import tech.fika.compose.multiplatform.playground.domain.core.invoke
import tech.fika.compose.multiplatform.playground.domain.entities.Greeting
import tech.fika.compose.multiplatform.playground.domain.usecases.GetPlatformUseCase
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine

@Factory(binds = [SetupStateMachine::class])
class SetupStateMachine(
    getPlatformUseCase: GetPlatformUseCase,
    errorHandler: ErrorHandler,
) : StateMachine<SetupAction, SetupEvent, SetupState>({
    state<SetupState> {
        process<SetupAction.ClickBack> {
            send(SetupEvent.NavigateBack)
        }
    }

    state<SetupState.Initial> {
        onEnter { dispatch(SetupAction.OnStart) }
        process<SetupAction.OnStart> {
            transition { SetupState.Loading(name = state.name) }
        }
    }

    state<SetupState.Loading> {
        onEnter { dispatch(SetupAction.LoadPlatform) }
        process<SetupAction.LoadPlatform> {
            launch {
                errorHandler(with = getPlatformUseCase) {
                    it.execute()
                }.onSuccess {
                    dispatch(SetupAction.LoadPlatformSuccess(it))
                }.onFailure {
                    dispatch(SetupAction.LoadPlatformError(it))
                }
            }
        }
        process<SetupAction.LoadPlatformSuccess> {
            transition {
                SetupState.Stable(name = state.name, greeting = Greeting(action.platform), isShowContent = false)
            }
        }
    }

    state<SetupState.Stable> {
        process<SetupAction.ClickMe> {
            transition {
                state.copy(isShowContent = !state.isShowContent)
            }
        }
    }
})