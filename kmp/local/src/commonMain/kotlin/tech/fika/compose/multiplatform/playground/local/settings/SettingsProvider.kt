package tech.fika.compose.multiplatform.playground.local.settings

import com.russhwolf.settings.Settings
import tech.fika.compose.multiplatform.playground.domain.core.Provider

internal expect class SettingsProvider : Provider<Settings> {
    override fun invoke(): Settings
}
