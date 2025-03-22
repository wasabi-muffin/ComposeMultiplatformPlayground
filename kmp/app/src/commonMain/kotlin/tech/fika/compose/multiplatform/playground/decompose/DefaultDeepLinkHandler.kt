package tech.fika.compose.multiplatform.playground.decompose

import co.touchlab.kermit.Logger
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.navigation.DeepLink
import tech.fika.compose.multiplatform.playground.navigation.DeepLinkHandler
import tech.fika.compose.multiplatform.playground.navigation.Route
import tech.fika.compose.multiplatform.playground.navigation.Url

@Single
class DefaultDeepLinkHandler : DeepLinkHandler {
    override fun handle(url: String?): List<Route>? = url?.toDeepLink()?.createStack()

    private fun String.toDeepLink(): DeepLink? {
        val url = Url(this)
        Logger.d { "Url: $url" }
        return when (url.path) {
            "setup" -> DeepLink.Sample("")
            else -> null
        }
    }

    private fun DeepLink.createStack(): List<Route> = when (this) {
        is DeepLink.Sample -> listOf(Route.Initial(name = name), Route.Setup(name = name))
    }
}
