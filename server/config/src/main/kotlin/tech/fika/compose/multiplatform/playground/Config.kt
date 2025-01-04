package tech.fika.compose.multiplatform.playground

import io.ktor.server.config.ApplicationConfig
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

@Single(createdAtStart = true)
class Config(@Provided config: ApplicationConfig) {
    val postgresUrl = config.property("postgres.url").getString()
    val postgresUsername = config.property("postgres.username").getString()
    val postgresPassword = config.property("postgres.password").getString()
    val postgresDriver = config.property("postgres.driver").getString()
}
