package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val errors: List<ResponseError>,
    val body: T?,
    val resetToken: Boolean,
)

interface ApiAuthRequest {
    val token: String
}

interface PaginationRequest {
    val skip: Int
    val take: Int
}

@Serializable
data class ResponseError(
    val code: Int,
    val message: String,
    val target: String?,
    val systemException: String?,
    val innerException: String?,
    val stackTrace: String?,
)
