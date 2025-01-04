package tech.fika.compose.multiplatform.playground.controller

import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing

fun interface Controller {
    fun Routing.routes(): Route
}
