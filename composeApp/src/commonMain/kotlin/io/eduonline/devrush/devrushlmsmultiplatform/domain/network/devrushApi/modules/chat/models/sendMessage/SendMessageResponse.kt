package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.sendMessage


import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ResponseError
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.ChatApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getConversation.Target
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.MessageItem
import kotlinx.serialization.Serializable

@Serializable
data class SendMessageResponse(
    override val success: Boolean,
    override val errors: List<ResponseError>,
    override val body: SendMessageResponseData?,
    override val resetToken: Boolean,
) : ChatApiResponse<SendMessageResponseData>


@Serializable
data class SendMessageResponseData(
    val member: Target,
    val messages: List<MessageItem>?,
)