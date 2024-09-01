package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation

import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.ChatModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.ChatModuleDelegate
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getConversation.GetConversationResponseData
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.GetMessagesRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.MessageItem
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.sendMessage.SendMessageRequest
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatViewState
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain.UploadFileUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent


enum class SentStatus {
    IS_SENT,
    IS_DELIVERED,
    IS_READ
}

class ChatViewModel(
    private val chatModule: ChatModule,
    private val uploadFileUseCase: UploadFileUseCase,
) : BaseViewModel<ChatViewState, ChatAction, ChatEvent>(
    ChatViewState()
), KoinComponent {

    override fun obtainEvent(viewEvent: ChatEvent) {
        when (viewEvent) {
            ChatEvent.GetMessages -> {}
            ChatEvent.InitConnection -> initConnection()
            ChatEvent.ForceConnect -> forceConnect()
            ChatEvent.CloseConnection -> stopConnection()
            ChatEvent.PaperclipClicked -> viewAction = ChatAction.ChosePhoto
            is ChatEvent.SendMessage -> sendMessage(viewEvent.message,viewEvent.replyID)
            is ChatEvent.Refresh -> {
                getMessages(skip = viewState.lastIndex)
                viewState = viewState.copy(isRefresh = false)
            }

            is ChatEvent.ChooseData -> uploadPhoto(viewEvent.data)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    private val delegate: ChatModuleDelegate = object : ChatModuleDelegate {
        override suspend fun didReceiveChatInfo(
            chatInfo: GetConversationResponseData,
            userId: String,
        ) {
            viewState = viewState.copy(
                chatInfo = chatInfo,
                chatMembers = chatInfo.members ?: emptyList(),
                lastIndex = chatInfo.lastMessage?.index ?: 0,
                lastSeenIndex = chatInfo.lastSeenMessageIndex ?: 0,
                currentUserInfo = chatInfo.members?.find { it.userId == userId }
            )
        }

        @OptIn(InternalCoroutinesApi::class)
        val lock = SynchronizedObject()
        private var showTime: Int = 0
            get() = synchronized(lock) { field }
            set(value) = synchronized(lock) { field = value }

        override suspend fun onMemberTyping(userId: String) {
            showTime += 5
            viewState = viewState.copy(
                printersId = userId,
            )
            CoroutineScope(Dispatchers.IO).launch {
                repeat(5) {
                    delay(1000L)
                    showTime -= 1
                }
                if (showTime == 0) {
                    viewState = viewState.copy(
                        printersId = null,
                    )
                }
            }
        }

        override fun onConnectionFail() = forceConnect()

        override fun onMessageCreated() = getMessages(skip = viewState.lastIndex)

        override suspend fun receiveNewMessageV2(
            messages: List<MessageItem>,
        ) = withContext(Dispatchers.Main) {
            Logger.d {
                "receiveNewMessageV2: $messages"
            }

            viewState = viewState.copy(
                chatItems = viewState.chatItems + messages,
                lastIndex = messages.last().index
            )
        }

        override suspend fun onConnected() {
            if (viewState.fetchChatRequest !is ChatLoadingState.Idle)
                getMessages(take = viewState.lastIndex + 10)
            else getMessages(skip = viewState.lastSeenIndex, take = 100)
        }

        override suspend fun receiveNewMessage(
            messages: List<MessageItem>,
        ) = withContext(Dispatchers.Main) {
            Logger.d { "receiveNewMessage: $messages" }

            viewState = viewState.copy(
                chatItems = viewState.chatItems + messages,
                lastIndex = messages.last().index
            )
        }

        override fun updateTargetLastSeenMessage(id: Int) {
            viewState = viewState.copy(lastSeenIndex = id)
            Logger.d { "updateTargetLastSeenMessage: ${viewState.lastIndex}" }
        }
    }

    init {
        chatModule.setDelegate(delegate)
    }

    private fun initConnection() {
        safeExecute(
            scope = viewModelScope,
            block = {
                chatModule.connectIfNot()
            }
        )
    }

    private fun forceConnect() {
        safeExecute(
            scope = viewModelScope,
            block = {
                chatModule.connect()
            }
        )
    }


    private fun stopConnection() {
        safeExecute(
            scope = viewModelScope,
            block = {
                updateFetchState(ChatLoadingState.Idle)
                chatModule.close()
            }
        )
    }


    private fun sendMessage(message: String,replyID:String?=null, data: String? = null) {
        safeExecute(
            scope = viewModelScope,
            block = {
                val response = chatModule.sendMessage(
                    SendMessageRequest(
                        date = Clock.System.now().toString(),
                        text = message,
                        isDraft = true,
                        replyId = replyID,
                        fileIds = if (data != null) listOf(data) else emptyList(),
                    )
                )

                val messages = response.body?.messages?.firstOrNull()?.let {
                    viewState = viewState.copy(
                        chatItems = viewState.chatItems + it
                    )
                }
            }
        ) {
            Logger.d("d") { it.message.toString() }
        }
    }

    private fun getMessages(skip: Int = 0, take: Int = 10,checkID:String="") {
        safeExecute(
            scope = viewModelScope,
            block = {
                val messages = chatModule.getMessages(
                    GetMessagesRequest(skip = skip, take = take, conversationId = checkID)
                )

                withContext(Dispatchers.Main) {
                    viewState = viewState.copy(
                        chatItems = viewState.chatItems + messages,
                    )
                }

                updateFetchState(ChatLoadingState.Success)
                refreshing(false)
            }
        )
    }

    private fun uploadPhoto(data: ByteArray) {
        safeExecute(scope = viewModelScope,
            block = {
                val uploadResponse = uploadFileUseCase(data)
                sendMessage(
                    message = uploadResponse.first,
                    data = uploadResponse.first
                )
            }
        ) {}
    }

    private fun refreshing(value: Boolean) {
        viewState = viewState.copy(isRefresh = value)
    }

    private fun updateFetchState(state: ChatLoadingState) {
        viewState = viewState.copy(fetchChatRequest = state)
    }

}