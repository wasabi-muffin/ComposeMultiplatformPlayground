package tech.fika.compose.multiplatform.playground

import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.koin.compose.KoinApplication
import org.koin.ksp.generated.module

fun main() {
    ComposeViewport(document.body!!) {
        KoinApplication(
            application = { modules(ApplicationModule().module) }
        ) {
            App()
        }
    }
}
