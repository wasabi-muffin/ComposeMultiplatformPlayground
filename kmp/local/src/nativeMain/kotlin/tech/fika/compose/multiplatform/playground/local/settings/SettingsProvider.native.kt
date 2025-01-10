package tech.fika.compose.multiplatform.playground.local.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.annotation.Single
import platform.Foundation.NSUserDefaults
import tech.fika.compose.multiplatform.playground.domain.core.Provider

@Single
internal actual class SettingsProvider : Provider<Settings> {
    actual override fun invoke(): Settings {
        val delegate = NSUserDefaults()
        return NSUserDefaultsSettings(delegate = delegate)
    }
}