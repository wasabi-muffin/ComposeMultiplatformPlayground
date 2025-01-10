package tech.fika.compose.multiplatform.playground.domain.core

fun interface ErrorHandlerScope {
    fun handle(error: Throwable): DomainError
}

inline operator fun <T> ErrorHandlerScope.invoke(
    block: ErrorHandlerScope.() -> T,
): DomainResult<T> = runCatching(block).fold(
    onSuccess = { DomainResult.Success(data = it) },
    onFailure = { DomainResult.Failure(error = handle(it)) },
)

inline operator fun <R, T> ErrorHandlerScope.invoke(
    with: R,
    block: R.(ErrorHandlerScope) -> T,
): DomainResult<T> = runCatching {
    with.block(this@invoke)
}.fold(
    onSuccess = { DomainResult.Success(data = it) },
    onFailure = { DomainResult.Failure(error = handle(it)) },
)
