package tech.fika.compose.multiplatform.playground.presentation.stateSaver

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State

@Suppress("UnusedReceiverParameter")
val SavedStateHandle.STATE_KEY: String
    get() = "STATE_KEY"

inline fun <reified S : State> SavedStateHandle.setState(state: S) {
    this[STATE_KEY] = Json.encodeToString(state)
}

inline fun <reified S : State> SavedStateHandle.getState(): S? {
    return get<String>(STATE_KEY)?.let { Json.decodeFromString(it) }
}
