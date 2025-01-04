package tech.fika.compose.multiplatform.playground

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.compose.KoinApplication
import org.koin.ksp.generated.module

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMultiplatformPlayground",
    ) {
        KoinApplication(
            application = { modules(ApplicationModule().module) }
        ) {
            App()
        }
    }
}