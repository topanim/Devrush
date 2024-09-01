package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.init

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ResponseError
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.ChatApiResponse
import kotlinx.serialization.Serializable


@Serializable
data class InitResponse(
    override val success: Boolean,
    override val errors: List<ResponseError>,
    override val body: InitResponseData?,
    override val resetToken: Boolean,
) : ChatApiResponse<InitResponseData>

@Serializable
data class InitResponseData(
    val user: InitUser,
    val conversationId: String,
//    val counters: Any?
)

@Serializable
data class InitUser(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String?,
    val phone: String?,
    val adminId: String?,
    val studentId: String,
    val avatar: InitUserAvatar?,
    val roles: List<Int>,
)

@Serializable
data class InitUserAvatar(
    val id: String?,
    val cloudKey: String?,
)