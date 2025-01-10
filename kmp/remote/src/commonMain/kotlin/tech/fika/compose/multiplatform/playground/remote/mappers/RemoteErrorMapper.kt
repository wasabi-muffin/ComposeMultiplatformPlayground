package tech.fika.compose.multiplatform.playground.remote.mappers

import tech.fika.compose.multiplatform.playground.domain.core.DomainError
import tech.fika.compose.multiplatform.playground.domain.core.ErrorMapper

internal class RemoteErrorMapper : ErrorMapper {
    override fun Throwable.toDomainError(): DomainError = when (this) {
        is DomainError -> this
        else -> DomainError.Unknown(cause = this)
    }
}
