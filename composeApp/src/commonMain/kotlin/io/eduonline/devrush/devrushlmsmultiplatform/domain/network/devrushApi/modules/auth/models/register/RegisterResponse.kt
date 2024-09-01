package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val accessToken: String,
    val refreshToken: String,
)