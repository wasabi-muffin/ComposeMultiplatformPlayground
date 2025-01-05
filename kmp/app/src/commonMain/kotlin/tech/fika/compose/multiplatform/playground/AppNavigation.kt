package tech.fika.compose.multiplatform.playground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import tech.fika.compose.multiplatform.playground.play.ui.InitialRoute
import tech.fika.compose.multiplatform.playground.play.ui.InitialScreen
import tech.fika.compose.multiplatform.playground.routers.InitialRouter
import tech.fika.compose.multiplatform.playground.routers.SetupRouter
import tech.fika.compose.multiplatform.playground.setup.ui.SetupRoute
import tech.fika.compose.multiplatform.playground.setup.ui.SetupScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = InitialRoute,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<InitialRoute> {
            InitialScreen(
                viewModel = koinViewModel(),
                navigator = InitialRouter(navController)
            )
        }

        composable<SetupRoute> {
            SetupScreen(
                viewModel = koinViewModel(),
                navigator = SetupRouter(navController)
            )
        }
    }
}
