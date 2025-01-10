package tech.fika.compose.multiplatform.playground.local

import com.russhwolf.settings.Settings
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.DomainModule
import tech.fika.compose.multiplatform.playground.domain.core.Provider
import tech.fika.compose.multiplatform.playground.local.mappers.LocalErrorMapper

@Module(includes = [DomainModule::class])
@ComponentScan
class LocalModule {
    @Single
    internal fun provideSettings(
        settingsProvider: Provider<Settings>,
    ): Settings = settingsProvider()

    @Single
    @Named("Local")
    internal fun provideLocalErrorMapper() = LocalErrorMapper()
}
