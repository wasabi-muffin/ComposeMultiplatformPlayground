package tech.fika.compose.multiplatform.playground.navigation

fun interface DeepLinkHandler {
    fun handle(url: String?): List<Route>?
}
