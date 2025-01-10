package tech.fika.compose.multiplatform.playground.domain.usecases

import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.domain.core.ErrorHandlerScope
import tech.fika.compose.multiplatform.playground.domain.entities.Platform
import tech.fika.compose.multiplatform.playground.domain.services.PlatformLocalService
import tech.fika.compose.multiplatform.playground.domain.services.TestLocalService

fun interface GetPlatformUseCase {
    suspend fun ErrorHandlerScope.execute(): Platform
}

@Single
internal class DefaultGetPlatformUseCase(
    private val platformLocalService: PlatformLocalService,
    private val testLocalService: TestLocalService,
) : GetPlatformUseCase {
    override suspend fun ErrorHandlerScope.execute(): Platform {
        return if (testLocalService.getIsFirstLogin()) {
            testLocalService.setIsFirstLogin()
            Platform("First Login")
        } else {
            platformLocalService.getPlatform()
        }
    }
}
