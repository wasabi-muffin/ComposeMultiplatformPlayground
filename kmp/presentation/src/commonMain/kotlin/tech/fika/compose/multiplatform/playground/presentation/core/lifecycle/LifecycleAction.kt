package tech.fika.compose.multiplatform.playground.presentation.core.lifecycle

import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action

sealed interface LifecycleAction : Action {
    data object OnCreate : LifecycleAction
    data object OnStart : LifecycleAction
    data object OnResume : LifecycleAction
    data object OnPause : LifecycleAction
    data object OnStop : LifecycleAction
    data object OnDestroy : LifecycleAction
}