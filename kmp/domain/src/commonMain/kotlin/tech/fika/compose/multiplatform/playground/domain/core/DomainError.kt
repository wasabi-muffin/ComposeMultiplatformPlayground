package tech.fika.compose.multiplatform.playground.domain.core

import kotlinx.serialization.Serializable

@Serializable
sealed class DomainError : Throwable() {
    abstract override val message: String?

    @Serializable
    sealed class Local : DomainError() {
        @Serializable
        data class MissingSettings(override val message: String? = null) : Local()
    }

    @Serializable
    data class Unknown(override val message: String? = null) : DomainError()
}
