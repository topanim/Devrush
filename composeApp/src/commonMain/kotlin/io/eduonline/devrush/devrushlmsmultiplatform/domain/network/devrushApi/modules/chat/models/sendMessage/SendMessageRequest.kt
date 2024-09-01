package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.sendMessage

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


enum class MessageType {
    @SerialName("normal")
    NORMAL,

    @SerialName("voice")
    VOICE
}

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class SendMessageRequest(
    val date: String,
    val isDraft: Boolean,
    val text: String,
    val fileIds: List<String> = emptyList(),
    val type: MessageType = MessageType.NORMAL,
    val conversationId: String = "",
    @EncodeDefault(EncodeDefault.Mode.NEVER) val replyId: String? = null
)