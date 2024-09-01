package io.eduonline.devrush.devrushlmsmultiplatform.domain.auth

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.AuthModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.refreshToken.RefreshTokenRequest
import io.github.xxfast.kstore.KStore
import kotlinx.serialization.Serializable


class AuthTokenRepository(
    private val api: AuthModule,
    private val store: KStore<Auth>,
) {
    @Serializable
    data class Auth(
        val accessToken: String,
        val refreshToken: String,
    )


    suspend fun accessToken() = getTokens().accessToken
    suspend fun refreshToken() = getTokens().refreshToken

    suspend fun getTokens(): Auth = store.get() ?: throw Exception("Tokens not found")

    suspend fun getTokensOrNull(): Auth? = store.get()

    suspend fun clearTokens() = store.delete()

    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
    ) = store.set(Auth(accessToken, refreshToken))

    suspend fun refreshTokens() {
        val refreshResponse = api.refreshToken(
            RefreshTokenRequest(
                token = store.get()?.refreshToken ?: ""
            )
        )

        refreshResponse.body?.let {
            saveTokens(it.accessToken, it.refreshToken)
        }
    }

}