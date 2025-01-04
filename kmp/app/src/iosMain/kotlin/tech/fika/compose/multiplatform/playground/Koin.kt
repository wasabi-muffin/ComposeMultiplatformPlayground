package tech.fika.compose.multiplatform.playground

import org.koin.core.context.startKoin
import org.koin.core.module.Module

object Koin {
    fun startKoin(modules: List<Module>) {
        startKoin {
            modules(modules)
        }
    }
}