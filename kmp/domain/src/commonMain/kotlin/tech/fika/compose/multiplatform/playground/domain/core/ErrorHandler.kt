package tech.fika.compose.multiplatform.playground.domain.core

import org.koin.core.annotation.Single

@Single
class ErrorHandler(private val errorMappers: List<ErrorMapper>) : ErrorHandlerScope {
    override fun handle(error: Throwable): DomainError {
        errorMappers.iterator().forEach { mapper ->
            val nextError = with(mapper) { error.toDomainError() }
            if (nextError !is DomainError.Unknown) return nextError
        }
        return DomainError.Unknown(cause = error)
    }
}
