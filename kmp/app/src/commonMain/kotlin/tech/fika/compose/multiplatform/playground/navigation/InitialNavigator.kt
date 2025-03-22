package tech.fika.compose.multiplatform.playground.navigation

import androidx.navigation.NavController
import tech.fika.compose.multiplatform.playground.play.ui.InitialRouter

internal fun NavController.initialRouter(): InitialRouter = object : InitialRouter {
    override fun setup(name: String) {
        navigate(route = Route.Setup(name = name))
    }
}
