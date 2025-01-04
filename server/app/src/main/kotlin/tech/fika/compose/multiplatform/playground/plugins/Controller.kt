package tech.fika.compose.multiplatform.playground.plugins

import io.ktor.server.application.Application
import org.koin.ktor.ext.inject
import tech.fika.compose.multiplatform.playground.controller.ControllerAggregator

fun Application.registerControllers() {
    val controllers: ControllerAggregator by inject()
    controllers.register(application = this)
}
