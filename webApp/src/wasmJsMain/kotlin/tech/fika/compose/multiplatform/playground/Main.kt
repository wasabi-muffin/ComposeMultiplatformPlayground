package tech.fika.compose.multiplatform.playground

import androidx.compose.ui.window.ComposeViewport
import co.touchlab.kermit.Logger
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.router.webhistory.withWebHistory
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import kotlinx.browser.document
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.w3c.dom.Document
import tech.fika.compose.multiplatform.playground.decompose.App
import tech.fika.compose.multiplatform.playground.decompose.DefaultRootComponent

fun main() {
    startKoin {
        modules(ApplicationModule().module)
    }

    val lifecycle = LifecycleRegistry()
    val rootComponent = withWebHistory { _, deepLink ->
        Logger.d { "Url: $deepLink" }

        DefaultRootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            deepLink = deepLink,
        )
    }

    lifecycle.attachToDocument()

    ComposeViewport(document.body!!) {
        App(component = rootComponent)
    }
}

private fun LifecycleRegistry.attachToDocument() {
    fun onVisibilityChanged() {
        if (visibilityState(document) == "visible") {
            resume()
        } else {
            stop()
        }
    }

    onVisibilityChanged()

    document.addEventListener(type = "visibilitychange", callback = { onVisibilityChanged() })
}

// Workaround for Document#visibilityState not available in Wasm
@JsFun("(document) => document.visibilityState")
private external fun visibilityState(document: Document): String