package tech.fika.compose.multiplatform.playground.routers

import androidx.navigation.NavController
import tech.fika.compose.multiplatform.playground.setup.ui.SetupNavigator

class SetupRouter(private val navController: NavController) : SetupNavigator {
    override fun back() {
        navController.popBackStack()
    }
}
