package tech.fika.compose.multiplatform.playground.data.local

import org.koin.core.annotation.Single
import platform.UIKit.UIDevice
import tech.fika.compose.multiplatform.playground.domain.entities.Platform
import tech.fika.compose.multiplatform.playground.domain.repositories.PlatformLocalRepository

@Single
internal actual class DefaultPlatformLocalRepository : PlatformLocalRepository {
    actual override fun getPlatform(): Platform = Platform(
        name = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    )
}