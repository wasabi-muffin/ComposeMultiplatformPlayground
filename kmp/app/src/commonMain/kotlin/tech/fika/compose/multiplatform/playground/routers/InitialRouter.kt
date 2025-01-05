package tech.fika.compose.multiplatform.playground.routers

import androidx.navigation.NavController
import tech.fika.compose.multiplatform.playground.play.ui.InitialNavigator
import tech.fika.compose.multiplatform.playground.setup.ui.SetupRoute

class InitialRouter(
    private val navController: NavController,
) : InitialNavigator {
    override fun setup(name: String) {
        navController.navigate(route = SetupRoute(name = name))
    }
}
