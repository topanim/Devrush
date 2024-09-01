package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain

import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository

class LogoutUseCase(
    private val tokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke() {
        tokenRepository.clearTokens()
    }
}