package tech.fika.compose.multiplatform.playground.domain.repositories

interface TestLocalRepository {
    suspend fun getIsFirstLogin(): Boolean
    suspend fun setIsFirstLogin()
}