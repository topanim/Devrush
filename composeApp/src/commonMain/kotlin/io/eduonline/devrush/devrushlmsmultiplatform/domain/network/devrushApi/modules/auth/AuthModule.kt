package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.HEADER_REFERER
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.authHeader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.forgotPassword.ForgotPasswordResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.login.LoginRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.login.LoginResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.logout.LogoutRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.logout.LogoutResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.refreshToken.RefreshTokenRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.refreshToken.RefreshTokenResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.register.RegisterRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.register.RegisterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthModule(
    override val client: HttpClient,
    override val domain: String,
    override val path: String,
) : ApiModule {
    private val modulePath = "$domain/$path/$MODULE"

    companion object {
        const val MODULE = "authorization"
        const val LOGIN_REFERER_URL = "https://devrush.eduonline.io/login?returnUrl=/"
        const val REGISTER_REFERER_URL = "https://devrush.eduonline.io/register"
        const val REFRESH_TOKEN_REFERER_URL = "https://devrush.eduonline.io"
        const val GET_ME_REFERER_URL = "https://devrush.eduonline.io/"
        const val LOGOUT_REFERER_URL = "https://devrush.eduonline.io"
        const val FORGOT_PASSWORD_REFERER_URL = "https://devrush.eduonline.io"
    }

    suspend fun login(
        data: LoginRequest,
    ) = client.post("$modulePath/login") {
        header(HEADER_REFERER, LOGIN_REFERER_URL)
        contentType(ContentType.Application.Json)

        setBody(data)
    }.body<ApiResponse<LoginResponse>>()

    suspend fun register(
        data: RegisterRequest,
    ) = client.post("$modulePath/register") {
        header(HEADER_REFERER, REGISTER_REFERER_URL)
        contentType(ContentType.Application.Json)

        setBody(data)
    }.body<ApiResponse<RegisterResponse>>()

    suspend fun refreshToken(
        data: RefreshTokenRequest,
    ) = client.put("$modulePath/refresh") {
        header(HEADER_REFERER, REFRESH_TOKEN_REFERER_URL)
        contentType(ContentType.Application.Json)

        setBody(data)
    }.body<ApiResponse<RefreshTokenResponse>>()

    suspend fun getMe(
        data: GetMeRequest,
    ) = client.get("$modulePath/me") {
        header(HEADER_REFERER, GET_ME_REFERER_URL)
        authHeader(data)
    }.body<ApiResponse<GetMeResponse>>()

    suspend fun logout(
        data: LogoutRequest,
    ) = client.put("$modulePath/logout") {
        header(HEADER_REFERER, LOGOUT_REFERER_URL)
        authHeader(data)
    }.body<ApiResponse<LogoutResponse>>()

    suspend fun forgotPassword(
        data: io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.forgotPassword.ForgotPasswordRequest,
    ) = client.put("$modulePath/forgot-password") {
        header(HEADER_REFERER, FORGOT_PASSWORD_REFERER_URL)

        contentType(ContentType.Application.Json)
        setBody(data)
    }.body<ApiResponse<ForgotPasswordResponse>>()
}