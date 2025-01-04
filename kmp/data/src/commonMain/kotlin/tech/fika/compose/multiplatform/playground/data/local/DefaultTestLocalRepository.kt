package tech.fika.compose.multiplatform.playground.data.local

import com.russhwolf.settings.Settings
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.data.settings.Keys
import tech.fika.compose.multiplatform.playground.data.settings.TypedSettings
import tech.fika.compose.multiplatform.playground.domain.repositories.TestLocalRepository

@Single
internal class DefaultTestLocalRepository(
    private val settings: Settings,
) : TestLocalRepository {
    override suspend fun getIsFirstLogin(): Boolean = with(TypedSettings) {
        !settings.contains(Keys.isFirstLogin)
    }

    override suspend fun setIsFirstLogin() = with(TypedSettings) {
        settings[Keys.isFirstLogin] = false
    }
}