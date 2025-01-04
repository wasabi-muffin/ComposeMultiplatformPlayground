package tech.fika.compose.multiplatform.playground

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.data.DataModule
import tech.fika.compose.multiplatform.playground.domain.DomainModule
import tech.fika.compose.multiplatform.playground.play.InitialModule
import tech.fika.compose.multiplatform.playground.presentation.core.tools.DefaultMessageManager
import tech.fika.compose.multiplatform.playground.presentation.core.tools.MessageManager
import tech.fika.compose.multiplatform.playground.setup.SetupModule

@Module(
    includes = [
        DomainModule::class,
        DataModule::class,
        InitialModule::class,
        SetupModule::class,
    ]
)
@ComponentScan
class ApplicationModule {
    @Single
    internal fun provideMessageManager(): MessageManager = DefaultMessageManager()
}
