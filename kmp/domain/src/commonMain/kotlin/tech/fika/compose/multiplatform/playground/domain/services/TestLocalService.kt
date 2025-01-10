package tech.fika.compose.multiplatform.playground.domain.services

interface TestLocalService {
    suspend fun getIsFirstLogin(): Boolean
    suspend fun setIsFirstLogin()
}