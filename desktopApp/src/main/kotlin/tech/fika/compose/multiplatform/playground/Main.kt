package tech.fika.compose.multiplatform.playground

import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import tech.fika.compose.multiplatform.playground.decompose.DefaultRootComponent
import tech.fika.compose.multiplatform.playground.decompose.App
import javax.swing.SwingUtilities

fun main() {
    startKoin {
        modules(ApplicationModule().module)
    }

    val lifecycle = LifecycleRegistry()

    val rootComponent = runOnUiThread {
        DefaultRootComponent(DefaultComponentContext(lifecycle))
    }

    application {
        val windowState = rememberWindowState()
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Decompose Quick Guide",
        ) {
            LifecycleController(
                lifecycleRegistry = lifecycle,
                windowState = windowState,
                windowInfo = LocalWindowInfo.current,
            )
            App(component = rootComponent)
        }
    }
}

internal fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}