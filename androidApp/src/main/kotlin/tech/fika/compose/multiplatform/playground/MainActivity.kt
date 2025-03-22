package tech.fika.compose.multiplatform.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.arkivanov.decompose.defaultComponentContext
import tech.fika.compose.multiplatform.playground.decompose.DefaultRootComponent
import tech.fika.compose.multiplatform.playground.decompose.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))

        val root = DefaultRootComponent(
            componentContext = defaultComponentContext(),
        )

        setContent {
            MaterialTheme {
                App(component = root)
            }
        }
    }
}
