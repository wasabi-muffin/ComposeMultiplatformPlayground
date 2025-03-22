package tech.fika.compose.multiplatform.playground.decompose

import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.push
import tech.fika.compose.multiplatform.playground.navigation.Route
import tech.fika.compose.multiplatform.playground.navigation.StackNavigator
import tech.fika.compose.multiplatform.playground.play.ui.InitialRouter

@OptIn(DelicateDecomposeApi::class)
class InitialNavigator(
    private val navigator: StackNavigator,
) : InitialRouter {
    override fun setup(name: String) {
        navigator.navigation.push(Route.Setup(name = name))
    }
}
