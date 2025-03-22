package tech.fika.compose.multiplatform.playground.navigation

sealed interface DeepLink {
    data class Sample(val name: String) : DeepLink
}