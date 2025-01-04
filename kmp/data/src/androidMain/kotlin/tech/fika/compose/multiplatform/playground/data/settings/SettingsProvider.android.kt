package tech.fika.compose.multiplatform.playground.data.settings

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.core.Provider

@Single
internal actual class SettingsProvider(@Provided private val context: Context) : Provider<Settings> {
    actual override fun invoke(): Settings {
        val delegate = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(delegate = delegate)
    }
}
