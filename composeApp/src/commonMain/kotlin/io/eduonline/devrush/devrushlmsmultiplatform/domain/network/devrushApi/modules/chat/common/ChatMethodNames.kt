package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common

import kotlinx.serialization.SerialName

sealed class ChatMethodNames(val value: String) {
    data object Init : ChatMethodNames("Init")
    data object GetConversation : ChatMethodNames("GetConversation")
    data object GetMessages : ChatMethodNames("GetMessages")
    data object SendMessage : ChatMethodNames("SendMessage")
    data object ConversationsMy : ChatMethodNames("Conversations My")
    data object SyncConversation : ChatMethodNames("SyncConversation")
    data class ConversationUpdated(val conversationId: String) : ChatMethodNames("Conversation $conversationId")
}



enum class ChatTypesNames {
    @SerialName("conversationUpdated")
    ConversationUpdated,

    @SerialName("conversationSynced")
    ConversationSynced,

    @SerialName("memberTyping")
    MemberTyping,

    @SerialName("messageCreated")
    MessageCreated
}
