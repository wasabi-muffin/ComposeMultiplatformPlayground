package tech.fika.compose.multiplatform.playground.navigation

import androidx.navigation.NavController
import tech.fika.compose.multiplatform.playground.play.ui.InitialNavigator
import tech.fika.compose.multiplatform.playground.setup.ui.SetupRoute

internal fun NavController.initialNavigator(): InitialNavigator = object : InitialNavigator {
    override fun setup(name: String) {
        navigate(route = SetupRoute(name = name))
    }
}