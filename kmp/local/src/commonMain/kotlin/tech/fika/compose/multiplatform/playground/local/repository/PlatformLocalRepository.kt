package tech.fika.compose.multiplatform.playground.local.repository

import tech.fika.compose.multiplatform.playground.domain.entities.Platform
import tech.fika.compose.multiplatform.playground.domain.services.PlatformLocalService

internal expect class PlatformLocalRepository() : PlatformLocalService {
    override fun getPlatform(): Platform
}
