package tech.fika.compose.multiplatform.playground.local.repository

import com.russhwolf.settings.Settings
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.local.settings.Keys
import tech.fika.compose.multiplatform.playground.local.settings.TypedSettings
import tech.fika.compose.multiplatform.playground.domain.services.TestLocalService

@Single
internal class TestLocalRepository(
    private val settings: Settings,
) : TestLocalService {
    override suspend fun getIsFirstLogin(): Boolean = with(TypedSettings) {
        settings[Keys.isFirstLogin] ?: true
    }

    override suspend fun setIsFirstLogin() = with(TypedSettings) {
        settings[Keys.isFirstLogin] = false
    }
}
