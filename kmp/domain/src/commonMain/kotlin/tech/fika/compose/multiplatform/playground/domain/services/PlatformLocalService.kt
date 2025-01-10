package tech.fika.compose.multiplatform.playground.domain.services

import tech.fika.compose.multiplatform.playground.domain.entities.Platform

interface PlatformLocalService {
    fun getPlatform(): Platform
}
