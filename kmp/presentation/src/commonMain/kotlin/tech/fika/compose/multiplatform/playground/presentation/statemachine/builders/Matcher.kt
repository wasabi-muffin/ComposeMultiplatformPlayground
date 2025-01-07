package tech.fika.compose.multiplatform.playground.presentation.statemachine.builders

import kotlin.reflect.KClass

class Matcher<T : Any, out R : T> private constructor(private val kClass: KClass<R>) {
    private val predicates = mutableListOf<(T) -> Boolean>({ kClass.isInstance(it) })
    fun matches(value: T) = predicates.all { it(value) }

    companion object {
        fun <T : Any, R : T> any(kClass: KClass<R>): Matcher<T, R> = Matcher(kClass)
        inline fun <T : Any, reified R : T> any(): Matcher<T, R> = any(R::class)
    }
}
