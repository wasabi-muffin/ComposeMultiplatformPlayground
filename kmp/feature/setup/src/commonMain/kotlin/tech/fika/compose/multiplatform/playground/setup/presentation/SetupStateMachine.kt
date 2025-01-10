package tech.fika.compose.multiplatform.playground.setup.presentation

import org.koin.core.annotation.Factory
import tech.fika.compose.multiplatform.playground.domain.core.ErrorHandlerScope
import tech.fika.compose.multiplatform.playground.domain.core.invoke
import tech.fika.compose.multiplatform.playground.domain.entities.Greeting
import tech.fika.compose.multiplatform.playground.domain.usecases.GetPlatformUseCase
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Transition
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageRelay
import tech.fika.compose.multiplatform.playground.presentation.logging.LoggingInterceptor
import tech.fika.compose.multiplatform.playground.presentation.statemachine.components.StateMachine

@Factory(binds = [SetupStateMachine::class])
class SetupStateMachine(
    getPlatformUseCase: GetPlatformUseCase,
    errorHandler: ErrorHandlerScope,
    relay: MessageRelay,
) : StateMachine<SetupAction, SetupEvent, SetupState>({
    config {
        initialState = SetupState.Initial("")
        messageRelay = relay
        interceptors.add(LoggingInterceptor(tag = "Setup"))
    }

    lifecycle {
        onResume {
            when (state) {
                is SetupState.Stable -> dispatch(SetupAction.LoadPlatform)
                else -> Unit
            }
        }
    }

    state<SetupState> {
        process<SetupAction.ClickBack> {
            send(SetupEvent.NavigateBack)
        }
    }

    state<SetupState.Initial> {
        listener.onEnter { dispatch(SetupAction.OnStart) }

        process<SetupAction.OnStart> {
            transition { SetupState.Loading(name = state.name) }
        }
    }

    state<SetupState.Loading> {
        listener.onEnter { dispatch(SetupAction.LoadPlatform) }

        process<SetupAction.LoadPlatform> {
            launch {
                loadPlatform(getPlatformUseCase = getPlatformUseCase, errorHandler = errorHandler, dispatch = dispatch)
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
        process<SetupAction.LoadPlatform> {
            transition { SetupState.Loading(state.name) }
        }
    }
})

suspend fun loadPlatform(
    getPlatformUseCase: GetPlatformUseCase,
    errorHandler: ErrorHandlerScope,
    dispatch: (SetupAction) -> Transition.Empty,
) = errorHandler(with = getPlatformUseCase) {
    it.execute()
}.onSuccess {
    dispatch(SetupAction.LoadPlatformSuccess(it))
}.onFailure {
    dispatch(SetupAction.LoadPlatformError(it))
}

