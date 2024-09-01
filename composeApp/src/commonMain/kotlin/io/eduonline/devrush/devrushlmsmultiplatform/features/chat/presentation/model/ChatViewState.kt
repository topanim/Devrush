package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getConversation.GetConversationResponseData
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getConversation.Member
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.MessageItem

data class ChatViewState(
    val fetchChatRequest: ChatLoadingState = ChatLoadingState.Loading,
    val chatItems: List<MessageItem> = emptyList(),
    val chatInfo: GetConversationResponseData? = null,
    val currentUserInfo: Member? = null,
    val chatMembers: List<Member> = emptyList(),
    val lastIndex: Int = 0,
    val lastSeenIndex: Int = 0,
    val isRefresh: Boolean = false,
    val printersId: String? = null,

)

sealed interface ChatLoadingState {
    data object Idle: ChatLoadingState
    data object Loading : ChatLoadingState
    data object Success : ChatLoadingState
    data class Error(val message: String) : ChatLoadingState
}