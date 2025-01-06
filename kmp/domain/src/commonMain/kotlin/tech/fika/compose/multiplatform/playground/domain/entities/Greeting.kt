package tech.fika.compose.multiplatform.playground.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Greeting(private val platform: Platform) {
    val greet: String
        get() = "Hello, ${platform.name}!"
}
