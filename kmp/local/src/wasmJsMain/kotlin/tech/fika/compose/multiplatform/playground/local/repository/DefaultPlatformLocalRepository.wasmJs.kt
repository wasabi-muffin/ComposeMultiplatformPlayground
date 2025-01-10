package tech.fika.compose.multiplatform.playground.local.repository

import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.entities.Platform
import tech.fika.compose.multiplatform.playground.domain.services.PlatformLocalService

@Single
internal actual class PlatformLocalRepository : PlatformLocalService {
    actual override fun getPlatform(): Platform = Platform(name = "Web with Kotlin/Wasm")
}