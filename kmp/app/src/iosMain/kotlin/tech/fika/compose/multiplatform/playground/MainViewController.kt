package tech.fika.compose.multiplatform.playground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle
import tech.fika.compose.multiplatform.playground.decompose.DefaultRootComponent
import tech.fika.compose.multiplatform.playground.decompose.App

@Suppress("FunctionName")
fun MainViewController() = ComposeUIViewController {
    val rootComponent = remember {
        DefaultRootComponent(DefaultComponentContext(ApplicationLifecycle()))
    }
    App(rootComponent, Modifier.fillMaxSize())
}