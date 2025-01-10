package tech.fika.compose.multiplatform.playground

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.DomainModule
import tech.fika.compose.multiplatform.playground.local.LocalModule
import tech.fika.compose.multiplatform.playground.play.InitialModule
import tech.fika.compose.multiplatform.playground.presentation.core.message.MessageRelay
import tech.fika.compose.multiplatform.playground.remote.RemoteModule
import tech.fika.compose.multiplatform.playground.setup.SetupModule

@Module(
    includes = [
        LocalModule::class,
        RemoteModule::class,
        DomainModule::class,
        InitialModule::class,
        SetupModule::class,
    ]
)
@ComponentScan
class ApplicationModule {
    @Single
    internal fun provideMessageManager(): MessageRelay = MessageRelay.default()
}
