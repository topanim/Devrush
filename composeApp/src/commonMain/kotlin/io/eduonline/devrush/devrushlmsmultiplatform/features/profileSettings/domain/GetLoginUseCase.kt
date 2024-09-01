package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain

import  io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.AuthModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.login.LoginRequest

class GetLoginUseCase(
    private val api: AuthModule,
    private val tokenRepository: AuthTokenRepository
) {

    suspend operator fun invoke(password: String?, email: String) {
        if (password != null && password != "") {
            val response = api.login(
                LoginRequest(
                    email,
                    password,
                    rememberMe = true
                )
            )
            tokenRepository.saveTokens(
                response.body!!.accessToken,
                response.body.refreshToken
            )
        }
    }
}