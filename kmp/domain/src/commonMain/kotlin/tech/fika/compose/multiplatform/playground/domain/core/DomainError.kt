package tech.fika.compose.multiplatform.playground.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed class DomainError : Throwable() {
    @Serializable
    sealed class Local : DomainError() {
        @Serializable
        data class MissingSettings(
            override val message: String? = null,
            @Transient override val cause: Throwable? = null,
        ) : Local()
    }

    @Serializable
    data class Unknown(@Transient override val cause: Throwable? = null) : DomainError()
}
