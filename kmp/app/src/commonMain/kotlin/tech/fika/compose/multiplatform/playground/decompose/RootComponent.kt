package tech.fika.compose.multiplatform.playground.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.childStackWebNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.webhistory.WebNavigation
import com.arkivanov.decompose.router.webhistory.WebNavigationOwner
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import tech.fika.compose.multiplatform.playground.navigation.AnyStoreComponent
import tech.fika.compose.multiplatform.playground.navigation.DeepLinkHandler
import tech.fika.compose.multiplatform.playground.navigation.Route
import tech.fika.compose.multiplatform.playground.navigation.StackNavigator
import tech.fika.compose.multiplatform.playground.navigation.StoreComponent
import tech.fika.compose.multiplatform.playground.play.presentation.InitialStateMachine
import tech.fika.compose.multiplatform.playground.play.ui.InitialComponent
import tech.fika.compose.multiplatform.playground.setup.presentation.SetupStateMachine
import tech.fika.compose.multiplatform.playground.setup.ui.SetupComponent

@OptIn(ExperimentalDecomposeApi::class)
interface RootComponent : BackHandlerOwner, WebNavigationOwner {
    val childStack: Value<ChildStack<*, StoreComponent<*, *, *, *>>>
    fun onBackClicked()
    fun onBackClicked(toIndex: Int)
}

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val deepLink: String? = null,
) : RootComponent, KoinComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Route>()
    private val stackNavigator = StackNavigator(navigation) { childStack.value }
    private val deepLinkHandler: DeepLinkHandler = get()
    private val stack = childStack(
        source = navigation,
        serializer = Route.serializer(),
        initialStack = ::createInitialStack,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    override val childStack: Value<ChildStack<*, AnyStoreComponent>> = stack

    @OptIn(ExperimentalDecomposeApi::class)
    override val webNavigation: WebNavigation<*> =
        childStackWebNavigation(
            navigator = navigation,
            stack = stack,
            serializer = Route.serializer(),
            pathMapper = { it.configuration.path },
        )

    override fun onBackClicked() = navigation.pop()

    override fun onBackClicked(toIndex: Int) = navigation.popTo(index = toIndex)

    private fun createInitialStack(): List<Route> =
        deepLinkHandler.handle(url = deepLink) ?: listOf(Route.Initial())

    private fun createChild(
        route: Route,
        componentContext: ComponentContext,
    ): AnyStoreComponent = when (route) {
        is Route.Initial -> InitialComponent(
            componentContext = componentContext,
            stateMachine = get<InitialStateMachine>(),
            route = route,
            router = InitialNavigator(navigator = stackNavigator),
        )

        is Route.Setup -> SetupComponent(
            componentContext = componentContext,
            stateMachine = get<SetupStateMachine>(),
            route = route,
            router = SetupNavigator(navigator = stackNavigator),
        )

        else -> error("Destination not found")
    }
}
