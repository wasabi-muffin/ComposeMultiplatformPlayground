package tech.fika.compose.multiplatform.playground.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import tech.fika.compose.multiplatform.playground.play.ui.InitialScreen
import tech.fika.compose.multiplatform.playground.setup.ui.SetupScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Route.Initial,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<Route.Initial> {
            InitialScreen(
                viewModel = koinViewModel(),
                router = navController.initialRouter()
            )
        }

        composable<Route.Setup> {
            SetupScreen(
                viewModel = koinViewModel(),
                router = navController.setupRouter()
            )
        }
    }
}
