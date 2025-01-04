package tech.fika.compose.multiplatform.playground.data.local

import tech.fika.compose.multiplatform.playground.domain.entities.Platform
import tech.fika.compose.multiplatform.playground.domain.repositories.PlatformLocalRepository

internal expect class DefaultPlatformLocalRepository() : PlatformLocalRepository {
    override fun getPlatform(): Platform
}