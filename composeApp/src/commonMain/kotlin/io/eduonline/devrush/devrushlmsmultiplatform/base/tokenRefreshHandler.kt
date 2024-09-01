package io.eduonline.devrush.devrushlmsmultiplatform.base

import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ErrorCodes

suspend fun ApiResponse<*>.checkRefresh(tokenRepository: AuthTokenRepository) {
    if (this.resetToken) tokenRepository.refreshTokens()
    if (this.errors.firstOrNull()?.code == ErrorCodes.INVALID_TOKEN) {
        tokenRepository.refreshTokens()
        throw ExpiredTokenException
    }
}

suspend fun <T : Any> ApiResponse<T>.checkedRefresh(tokenRepository: AuthTokenRepository): ApiResponse<T> {
    this.checkRefresh(tokenRepository)
    return this
}