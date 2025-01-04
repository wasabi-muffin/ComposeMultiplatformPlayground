package tech.fika.compose.multiplatform.playground.domain.core

fun interface Provider<T> {
    operator fun invoke(): T
}