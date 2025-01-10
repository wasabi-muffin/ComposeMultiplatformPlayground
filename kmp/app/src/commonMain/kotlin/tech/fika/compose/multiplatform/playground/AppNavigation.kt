package tech.fika.compose.multiplatform.playground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import tech.fika.compose.multiplatform.playground.navigation.initialNavigator
import tech.fika.compose.multiplatform.playground.navigation.setupNavigator
import tech.fika.compose.multiplatform.playground.play.ui.InitialRoute
import tech.fika.compose.multiplatform.playground.play.ui.InitialScreen
import tech.fika.compose.multiplatform.playground.setup.ui.SetupRoute
import tech.fika.compose.multiplatform.playground.setup.ui.SetupScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = InitialRoute,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<InitialRoute> {
            InitialScreen(
                viewModel = koinViewModel(),
                navigator = navController.initialNavigator()
            )
        }

        composable<SetupRoute> {
            SetupScreen(
                viewModel = koinViewModel(),
                navigator = navController.setupNavigator()
            )
        }
    }
}
