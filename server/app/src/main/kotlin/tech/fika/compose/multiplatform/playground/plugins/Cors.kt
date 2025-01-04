package tech.fika.compose.multiplatform.playground.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.cors.routing.CORS

fun Application.setupCors() {
    install(CORS)
}
