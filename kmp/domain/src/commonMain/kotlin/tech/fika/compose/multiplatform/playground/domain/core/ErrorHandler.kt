package tech.fika.compose.multiplatform.playground.domain.core

interface ErrorHandlerScope

fun interface ErrorHandler : ErrorHandlerScope {
    fun handle(error: Throwable): DomainError
}

inline operator fun <T> ErrorHandler.invoke(
    block: ErrorHandlerScope.() -> T,
): DomainResult<T> = runCatching(block).fold(
    onSuccess = { DomainResult.Success(data = it) },
    onFailure = { DomainResult.Failure(error = handle(it)) },
)

inline operator fun <R, T> ErrorHandler.invoke(
    with: R,
    block: R.(ErrorHandlerScope) -> T,
): DomainResult<T> = runCatching {
    with.block(this@invoke)
}.fold(
    onSuccess = { DomainResult.Success(data = it) },
    onFailure = { DomainResult.Failure(error = handle(it)) },
)

fun combine(vararg errorHandler: ErrorHandler): ErrorHandler = ErrorHandler { error ->
    errorHandler.iterator().forEach { handler ->
        val nextError = handler.handle(error)
        if (nextError !is DomainError.Unknown) return@ErrorHandler nextError
    }
    return@ErrorHandler DomainError.Unknown(error.message)
}

operator fun ErrorHandler.plus(other: ErrorHandler): ErrorHandler = combine(this, other)
