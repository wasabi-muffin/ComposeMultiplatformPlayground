package tech.fika.compose.multiplatform.playground

import io.ktor.server.config.ApplicationConfig
import org.koin.core.module.Module
import org.koin.dsl.module

class ConfigModule(private val applicationConfig: ApplicationConfig) {
    val module: Module
        get() = module {
            single<ApplicationConfig> {
                applicationConfig
            }
        }
}