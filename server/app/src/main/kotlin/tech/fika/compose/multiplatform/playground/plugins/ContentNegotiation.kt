package tech.fika.compose.multiplatform.playground.plugins

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun Application.setupContentNegotiation() {
    install(ContentNegotiation) {
        json()
    }
}
