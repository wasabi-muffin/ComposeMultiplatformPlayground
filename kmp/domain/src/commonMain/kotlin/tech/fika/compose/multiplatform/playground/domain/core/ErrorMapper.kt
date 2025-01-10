package tech.fika.compose.multiplatform.playground.domain.core

fun interface ErrorMapper {
    fun Throwable.toDomainError(): DomainError
}
