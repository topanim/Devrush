package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,

    val lang: String,
    val timezoneOffset: String,

    val isAgreementAccepted: Boolean = true,

    val urlParameters: Map<String, String> = emptyMap(),
)