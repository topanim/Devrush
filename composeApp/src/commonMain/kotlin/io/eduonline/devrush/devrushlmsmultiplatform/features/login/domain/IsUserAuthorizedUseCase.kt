package io.eduonline.devrush.devrushlmsmultiplatform.features.login.domain

import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository

class IsUserAuthorizedUseCase(
    private val tokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(): Boolean {
        return tokenRepository.getTokensOrNull() != null
    }
}