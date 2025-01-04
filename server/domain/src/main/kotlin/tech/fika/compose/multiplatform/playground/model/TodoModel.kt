package tech.fika.compose.multiplatform.playground.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val id: String,
    val description: String,
)