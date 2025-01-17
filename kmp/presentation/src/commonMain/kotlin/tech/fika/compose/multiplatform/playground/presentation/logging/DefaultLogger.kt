package tech.fika.compose.multiplatform.playground.presentation.logging

import co.touchlab.kermit.Severity
import co.touchlab.kermit.Logger.Companion as Kermit

class DefaultLogger : Logger {
    override fun log(level: Logger.Level, tag: String, message: () -> String) {
        Kermit.log(
            severity = level.severity(),
            tag = tag,
            throwable = null,
            message = message()
        )
    }

    private fun Logger.Level.severity() = when (this) {
        Logger.Level.Verbose -> Severity.Verbose
        Logger.Level.Debug -> Severity.Debug
        Logger.Level.Info -> Severity.Info
        Logger.Level.Warn -> Severity.Warn
        Logger.Level.Error -> Severity.Error
        Logger.Level.Assert -> Severity.Assert
    }
}
