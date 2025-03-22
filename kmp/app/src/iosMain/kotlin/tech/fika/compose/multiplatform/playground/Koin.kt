package tech.fika.compose.multiplatform.playground

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

object Koin {
    fun startKoin(modules: List<Module>) {
        startKoin {
            modules(modules)
        }
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration? = null) = startKoin {
    appDeclaration?.invoke(this)
}
