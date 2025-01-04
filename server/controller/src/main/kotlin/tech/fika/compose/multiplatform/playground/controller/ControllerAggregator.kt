package tech.fika.compose.multiplatform.playground.controller

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.core.annotation.Single

@Single
class ControllerAggregator(private val controllers: List<Controller>) {
    fun register(application: Application) = application.routing {
        controllers.forEach { controller ->
            with(controller) {
                routes()
            }
        }
    }
}
