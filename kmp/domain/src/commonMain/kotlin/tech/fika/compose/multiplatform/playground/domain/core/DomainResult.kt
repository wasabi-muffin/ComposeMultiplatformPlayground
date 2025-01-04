package tech.fika.compose.multiplatform.playground.domain.core

sealed class DomainResult<out R> {
    data class Success<out T>(val data: T) : DomainResult<T>()
    data class Failure(val error: DomainError) : DomainResult<Nothing>()

    inline fun <T> fold(
        onSuccess: (R) -> T,
        onFailure: (DomainError) -> T,
    ): T = when (this) {
        is Success -> onSuccess(data)
        is Failure -> onFailure(error)
    }

    inline fun onSuccess(block: (R) -> Unit): DomainResult<R> = apply {
        if (this is Success) block(data)
    }

    inline fun <T> returnOnSuccess(block: (R) -> T): T? =
        if (this is Success) block(data) else null

    inline fun onFailure(block: (DomainError) -> Unit): DomainResult<R> = apply {
        if (this is Failure) block(error)
    }

    inline fun <T> returnOnFailure(block: (DomainError) -> T): T? =
        if (this is Failure) block(error) else null
}