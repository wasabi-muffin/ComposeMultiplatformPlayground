package tech.fika.compose.multiplatform.playground.navigation

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
@Serializable
sealed interface Route {
    val path: String

    @Serializable
    data class Initial(val name: String = "") : Route {
        override val path = "/"
    }

    @Serializable
    data class Setup(val name: String = "") : Route{
        override val path = "/setup"
    }
}
