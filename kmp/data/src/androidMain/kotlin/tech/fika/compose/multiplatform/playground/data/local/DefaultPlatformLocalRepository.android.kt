package tech.fika.compose.multiplatform.playground.data.local

import android.os.Build
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.entities.Platform
import tech.fika.compose.multiplatform.playground.domain.repositories.PlatformLocalRepository

@Single
internal actual class DefaultPlatformLocalRepository : PlatformLocalRepository {
    actual override fun getPlatform(): Platform = Platform(
        name = "Android ${Build.VERSION.SDK_INT}"
    )
}