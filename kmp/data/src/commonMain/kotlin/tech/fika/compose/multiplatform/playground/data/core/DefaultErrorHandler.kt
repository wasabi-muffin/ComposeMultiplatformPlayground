package tech.fika.compose.multiplatform.playground.data.core

import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.core.DomainError
import tech.fika.compose.multiplatform.playground.domain.core.ErrorHandler

@Single
internal class DefaultErrorHandler : ErrorHandler {
    override fun handle(error: Throwable): DomainError {
        return DomainError.Unknown(throwable = error)
    }
}
