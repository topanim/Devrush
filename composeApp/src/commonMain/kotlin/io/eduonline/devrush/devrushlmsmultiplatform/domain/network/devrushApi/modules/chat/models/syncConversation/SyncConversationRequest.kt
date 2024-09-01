package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.syncConversation

import kotlinx.serialization.Serializable

@Serializable
data class SyncConversationRequest(
    val id: String,
    val index: Int
)
