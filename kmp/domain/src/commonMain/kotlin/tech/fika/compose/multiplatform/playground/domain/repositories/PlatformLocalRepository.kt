package tech.fika.compose.multiplatform.playground.domain.repositories

import tech.fika.compose.multiplatform.playground.domain.entities.Platform

interface PlatformLocalRepository {
    fun getPlatform(): Platform
}
