package tech.fika.compose.multiplatform.playground

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import tech.fika.compose.multiplatform.playground.controller.ControllerModule
import tech.fika.compose.multiplatform.playground.local.LocalModule
import tech.fika.compose.multiplatform.playground.remote.RemoteModule

@Module(
    includes = [
        ControllerModule::class,
        DomainModule::class,
        LocalModule::class,
        RemoteModule::class
    ]
)
@ComponentScan
class ApplicationModule
