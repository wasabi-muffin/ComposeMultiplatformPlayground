package tech.fika.compose.multiplatform.playground.decompose

import com.arkivanov.decompose.router.stack.pop
import tech.fika.compose.multiplatform.playground.navigation.StackNavigator
import tech.fika.compose.multiplatform.playground.setup.ui.SetupRouter

class SetupNavigator(
    private val navigator: StackNavigator,
) : SetupRouter {
    override fun back() {
        navigator.navigation.pop()
    }
}
