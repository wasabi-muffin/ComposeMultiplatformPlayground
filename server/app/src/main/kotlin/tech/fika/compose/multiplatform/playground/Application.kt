package tech.fika.compose.multiplatform.playground

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import tech.fika.compose.multiplatform.playground.plugins.registerControllers
import tech.fika.compose.multiplatform.playground.plugins.setupContentNegotiation
import tech.fika.compose.multiplatform.playground.plugins.setupCors
import tech.fika.compose.multiplatform.playground.plugins.setupKoin

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    setupKoin()
    setupCors()
    setupContentNegotiation()
    registerControllers()
}
