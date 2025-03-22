package tech.fika.compose.multiplatform.playground.navigation

import kotlinx.serialization.Serializable

@Serializable
data class Url(
    val path: String,
    val pathSegments: List<String>,
    val parameters: Map<String, String>,
)

fun Url(url: String): Url {
    var path: String = url.substringAfter(delimiter = "://").substringAfter(delimiter = "/")
    var parameters: Map<String, String> = emptyMap()

    if ('?' in path) {
        parameters =
            path.substringAfter(delimiter = "?")
                .split("&")
                .map { it.split("=") }
                .associate { (key, value) -> key to value }

        path = path.substringBefore(delimiter = "?")
    }

    return Url(path = path, pathSegments = path.split("/"), parameters = parameters)
}
