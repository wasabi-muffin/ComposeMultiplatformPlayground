package tech.fika.compose.multiplatform.playground.navigation

import androidx.navigation.NavController
import tech.fika.compose.multiplatform.playground.setup.ui.SetupNavigator

internal fun NavController.setupNavigator(): SetupNavigator = object : SetupNavigator {
    override fun back() {
        popBackStack()
    }
}