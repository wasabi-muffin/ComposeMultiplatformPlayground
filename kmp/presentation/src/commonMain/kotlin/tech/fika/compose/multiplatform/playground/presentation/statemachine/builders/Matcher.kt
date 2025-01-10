package tech.fika.compose.multiplatform.playground.presentation.statemachine.builders

import kotlin.reflect.KClass

class Matcher<Parent : Any, out Child : Parent> private constructor(private val kClass: KClass<Child>) {
    private val predicates = mutableListOf<(Parent) -> Boolean>({ kClass.isInstance(it) })
    fun matches(value: Parent): Boolean = predicates.all { it(value) }

    companion object {
        fun <Parent : Any, Child : Parent> any(kClass: KClass<Child>): Matcher<Parent, Child> = Matcher(kClass)
        inline fun <Parent : Any, reified Child : Parent> any(): Matcher<Parent, Child> = any(Child::class)
    }
}
