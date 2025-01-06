package tech.fika.compose.multiplatform.playground.presentation.statemachine.builders

import tech.fika.compose.multiplatform.playground.presentation.core.components.Interceptor
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Action
import tech.fika.compose.multiplatform.playground.presentation.core.contract.Event
import tech.fika.compose.multiplatform.playground.presentation.core.contract.State
import tech.fika.compose.multiplatform.playground.presentation.statemachine.nodes.InterceptorNode

@StateMachineDsl
class InterceptorBuilder<A : Action, E : Event, S : State> {
    private val interceptors = mutableListOf<Interceptor<A, E, S>>()

    fun add(interceptor: Interceptor<A, E, S>) {
        interceptors.add(interceptor)
    }

    operator fun Interceptor<A, E, S>.unaryPlus() {
        interceptors.add(this)
    }

    fun build() = InterceptorNode(interceptors = interceptors)
}