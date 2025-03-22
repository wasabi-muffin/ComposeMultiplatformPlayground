package tech.fika.compose.multiplatform.playground.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation

data class StackNavigator(
    val navigation: StackNavigation<Route>,
    val currentStack: (() -> ChildStack<*, AnyStoreComponent>)? = null
)
