package tech.fika.compose.multiplatform.playground.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.Config
import tech.fika.compose.multiplatform.playground.database.Database

@Module
@ComponentScan
class LocalModule {
    @Single
    internal fun provideDatabase(driver: SqlDriver) = Database.invoke(driver = driver).also {
        Database.Schema.create(driver)
    }

    @Single
    internal fun provideSqlDriver(config: Config): SqlDriver = HikariDataSource(
        HikariConfig().apply {
            jdbcUrl = config.postgresUrl
            username = config.postgresUsername
            password = config.postgresPassword
            driverClassName = config.postgresDriver
        }
    ).asJdbcDriver()
}