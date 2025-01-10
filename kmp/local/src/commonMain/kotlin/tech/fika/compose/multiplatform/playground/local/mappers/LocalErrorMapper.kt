package tech.fika.compose.multiplatform.playground.local.mappers

import tech.fika.compose.multiplatform.playground.domain.core.DomainError
import tech.fika.compose.multiplatform.playground.domain.core.ErrorMapper

internal class LocalErrorMapper : ErrorMapper {
    override fun Throwable.toDomainError(): DomainError = when (this) {
        is DomainError -> this
        else -> DomainError.Unknown(cause = this)
    }
}
