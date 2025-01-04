package tech.fika.compose.multiplatform.playground

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import tech.fika.compose.multiplatform.playground.play.ui.InitialScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        InitialScreen()
    }
}