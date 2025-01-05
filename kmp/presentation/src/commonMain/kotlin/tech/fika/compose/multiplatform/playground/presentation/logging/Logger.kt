package tech.fika.compose.multiplatform.playground.presentation.logging

interface Logger {
    fun log(
        level: Level = Level.Debug,
        tag: String = "Macaron",
        message: () -> String,
    )

    enum class Level {
        Verbose,
        Debug,
        Info,
        Warn,
        Error,
        Assert
    }
}
