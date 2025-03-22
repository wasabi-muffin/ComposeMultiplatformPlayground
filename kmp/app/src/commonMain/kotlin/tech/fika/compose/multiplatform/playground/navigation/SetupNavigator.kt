package tech.fika.compose.multiplatform.playground.navigation

import androidx.navigation.NavController
import tech.fika.compose.multiplatform.playground.setup.ui.SetupRouter

internal fun NavController.setupRouter(): SetupRouter = object : SetupRouter {
    override fun back() {
        popBackStack()
    }
}
