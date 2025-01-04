package tech.fika.compose.multiplatform.playground.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import tech.fika.compose.multiplatform.playground.ApplicationModule
import tech.fika.compose.multiplatform.playground.ConfigModule

fun Application.setupKoin() {
    install(Koin) {
        slf4jLogger()
        modules(
            ConfigModule(applicationConfig = environment.config).module,
            ApplicationModule().module,
        )
    }
}
