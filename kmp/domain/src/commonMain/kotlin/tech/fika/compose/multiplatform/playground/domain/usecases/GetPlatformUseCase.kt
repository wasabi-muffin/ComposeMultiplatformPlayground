package tech.fika.compose.multiplatform.playground.domain.usecases

import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.core.ErrorHandlerScope
import tech.fika.compose.multiplatform.playground.domain.entities.Platform
import tech.fika.compose.multiplatform.playground.domain.repositories.PlatformLocalRepository
import tech.fika.compose.multiplatform.playground.domain.repositories.TestLocalRepository

fun interface GetPlatformUseCase {
    suspend fun ErrorHandlerScope.execute(): Platform
}

@Single
internal class DefaultGetPlatformUseCase(
    private val platformLocalRepository: PlatformLocalRepository,
    private val testLocalRepository: TestLocalRepository,
) : GetPlatformUseCase {
    override suspend fun ErrorHandlerScope.execute(): Platform {
        return if (testLocalRepository.getIsFirstLogin()) {
            Platform("First Login")
        } else {
            testLocalRepository.setIsFirstLogin()
            platformLocalRepository.getPlatform()
        }
    }
}
