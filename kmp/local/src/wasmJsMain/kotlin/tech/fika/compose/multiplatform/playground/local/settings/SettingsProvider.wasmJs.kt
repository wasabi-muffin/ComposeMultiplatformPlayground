package tech.fika.compose.multiplatform.playground.local.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings
import kotlinx.browser.localStorage
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.core.Provider

@Single
internal actual class SettingsProvider : Provider<Settings> {
    actual override fun invoke(): Settings {
        val delegate = localStorage
        return StorageSettings(delegate = delegate)
    }
}
