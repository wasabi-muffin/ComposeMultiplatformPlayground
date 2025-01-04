package tech.fika.compose.multiplatform.playground.domain.core

sealed class DomainError(throwable: Throwable?) : Throwable(
    message = throwable?.message,
    cause = throwable?.cause
) {
    sealed class Local(throwable: Throwable?) : DomainError(throwable) {
        data class MissingSettings(val throwable: Throwable? = null) : Local(throwable)
    }

    data class Unknown(val throwable: Throwable? = null) : DomainError(throwable)
}