package tech.fika.compose.multiplatform.playground.local.settings

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.core.Provider
import java.util.prefs.Preferences

@Single
internal actual class SettingsProvider : Provider<Settings> {
    actual override fun invoke(): Settings {
        val delegate = Preferences.systemRoot()
        return PreferencesSettings(delegate = delegate)
    }
}