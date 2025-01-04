package tech.fika.compose.multiplatform.playground.data

import com.russhwolf.settings.Settings
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.core.Provider

@Module
@ComponentScan
class DataModule {
    @Single
    internal fun provideHttpClient(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 10000L
            connectTimeoutMillis = 10000L
            socketTimeoutMillis = 10000L
        }
        install(Logging)
    }

    @Single
    internal fun provideSettings(
        settingsProvider: Provider<Settings>,
    ): Settings = settingsProvider()
}