package tech.fika.compose.multiplatform.playground.decompose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import tech.fika.compose.multiplatform.playground.play.ui.InitialComponent
import tech.fika.compose.multiplatform.playground.play.ui.InitialScreen

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun App(component: RootComponent, modifier: Modifier = Modifier) {
    MaterialTheme {
        Children(
            stack = component.childStack,
            modifier = modifier.fillMaxSize(),
            animation = predictiveBackAnimation(
                backHandler = component.backHandler,
                fallbackAnimation = stackAnimation(fade() + scale()),
                onBack = component::onBackClicked,
            ),
        ) { child ->
            child.instance.screen()
        }
    }
}
