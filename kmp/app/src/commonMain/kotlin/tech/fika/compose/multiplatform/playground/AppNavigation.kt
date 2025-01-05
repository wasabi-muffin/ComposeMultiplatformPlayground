package tech.fika.compose.multiplatform.playground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.koinInject
import tech.fika.compose.multiplatform.playground.play.ui.InitialRoute
import tech.fika.compose.multiplatform.playground.play.ui.InitialScreen
import tech.fika.compose.multiplatform.playground.routers.InitialRouter
import tech.fika.compose.multiplatform.playground.routers.SetupRouter
import tech.fika.compose.multiplatform.playground.setup.ui.SetupRoute
import tech.fika.compose.multiplatform.playground.setup.ui.SetupScreen


@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = InitialRoute,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<InitialRoute> {
            InitialScreen(
                stateMachine = koinInject(),
                route = it.toRoute(),
                navigator = InitialRouter(navController)
            )
        }

        composable<SetupRoute> {
            SetupScreen(
                stateMachine = koinInject(),
                route = it.toRoute(),
                navigator = SetupRouter(navController)
            )
        }
    }
}
