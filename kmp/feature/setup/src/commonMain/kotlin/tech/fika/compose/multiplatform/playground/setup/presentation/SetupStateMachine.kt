package tech.fika.compose.multiplatform.playground.setup.presentation

import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.core.ErrorHandler
import tech.fika.compose.multiplatform.playground.domain.core.invoke
import tech.fika.compose.multiplatform.playground.domain.entities.Greeting
import tech.fika.compose.multiplatform.playground.domain.usecases.GetPlatformUseCase
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.stateMachine

typealias SetupStateMachine = StateMachine<SetupAction, SetupEvent, SetupState>

@Single
internal fun setupStateMachine(
    getPlatformUseCase: GetPlatformUseCase,
    errorHandler: ErrorHandler,
): SetupStateMachine = stateMachine {
    initialState { SetupState.Initial }

    state<SetupState.Initial> {
        onEnter { dispatch(SetupAction.OnStart) }
        process<SetupAction.OnStart> {
            transition { SetupState.Loading }
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
                SetupState.Stable(greeting = Greeting(action.platform), isShowContent = false)
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
}
