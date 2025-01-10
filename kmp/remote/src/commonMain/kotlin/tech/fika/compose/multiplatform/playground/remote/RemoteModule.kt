package tech.fika.compose.multiplatform.playground.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.DomainModule
import tech.fika.compose.multiplatform.playground.remote.mappers.RemoteErrorMapper

@Module(includes = [DomainModule::class])
@ComponentScan
class RemoteModule {
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
    @Named("Remote")
    internal fun provideRemoteErrorMapper() = RemoteErrorMapper()
}
