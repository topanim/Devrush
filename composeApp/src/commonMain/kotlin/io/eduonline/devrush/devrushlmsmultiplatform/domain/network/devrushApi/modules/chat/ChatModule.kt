package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat

import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.authHeader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.ChatMethodNames
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.ChatTypesNames
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.conversationsMy.ConversationsMyResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getConversation.GetConversationResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getConversation.GetConversationResponseData
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.GetMessagesRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.GetMessagesResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.MessageItem
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.init.InitResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.negotiateChat.NegotiateChatRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.negotiateChat.NegotiateChatResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.sendMessage.SendMessageRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.sendMessage.SendMessageResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.syncConversation.SyncConversationRequest
import io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr.AutomaticReconnect
import io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr.HubConnection
import io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr.HubConnectionBuilder
import io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr.HubConnectionState
import io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr.TransportEnum
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

interface ChatModuleDelegate {
    suspend fun onConnected()

    //    fun didReceiveStudentId(studentId: String)
//    fun didReceiveConversationId(conversationId: String)
    suspend fun didReceiveChatInfo(chatInfo: GetConversationResponseData, userId: String)

    //    fun didReceiveMessages(chatInfo: ChatConversationResponse, messages: List<MessageItem>)
//    fun didSendMessage(message: MessageItem)
    suspend fun onMemberTyping(userId: String)
    fun onConnectionFail()
    fun onMessageCreated()
    suspend fun receiveNewMessageV2(messages: List<MessageItem>)
    suspend fun receiveNewMessage(messages: List<MessageItem>)
    fun updateTargetLastSeenMessage(id: Int)
}

class ChatModule(
    private val client: HttpClient,
    private val tokenRepository: AuthTokenRepository,
    private val coder: Json,
) {
    private var hubConnection: HubConnection? = null
    private var conversationId: String? = null
    private var conversation: GetConversationResponseData? = null
    private var delegate: ChatModuleDelegate? = null


    fun setDelegate(delegate: ChatModuleDelegate?) {
        this.delegate = delegate
    }

    suspend fun connectIfNot() {
        hubConnection ?: connect()
    }

    suspend fun connect() {
        val accessToken = tokenRepository.accessToken()
        val negotiationResponse = negotiateChat(
            NegotiateChatRequest(accessToken)
        )

        close()

        hubConnection = HubConnectionBuilder.create(
            "wss://chat.accelonline.io/hub?id=${negotiationResponse.connectionToken}&access_token=$accessToken",
            httpClient = client
        ) {
            transportEnum = TransportEnum.WebSockets
            skipNegotiate = true
            automaticReconnect = AutomaticReconnect.Active
            json = coder
            logger =
                io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr.Logger { severity, msg, cause ->
                    if (msg.contains("terminating")) delegate?.onConnectionFail()
                    Logger.d(
                        tag = "hub",
                        messageString = """
                            --hub message--
                            severity: $severity
                            msg: $msg
                            cause: $cause
                            --hub message end--
                        """.trimIndent()
                    )
                }
        }

        Logger.d("d") { "2" }
        Logger.d("d") { hubConnection!!.connectionState.value.toString() }
        hubConnection!!.start()
        Logger.d("d") { hubConnection!!.connectionState.value.toString() }
        Logger.d("d") { "3" }

        waitConnection()

        val initResponse = hubConnection!!.invoke(
            ChatMethodNames.Init.value,
            InitResponse::class
        )

        conversationId = initResponse.body!!.conversationId
        getConversation(initResponse.body.user.id)

        delegate?.onConnected()

        registerListeners()
    }

    private suspend fun getConversation(userId: String) {
        val conversationResponse = hubConnection!!.invoke(
            ChatMethodNames.GetConversation.value,
            conversationId!!,
            GetConversationResponse::class
        )
        conversation = conversationResponse.body
        delegate?.didReceiveChatInfo(conversation!!, userId)
    }

    private suspend fun waitConnection() {
        while (hubConnection?.connectionState?.value != HubConnectionState.CONNECTED)
            delay(100L)
    }

    private suspend fun registerListeners() = try {
        hubConnection!!.on(
            ChatMethodNames.ConversationsMy.value,
            ConversationsMyResponse::class,
            Unit::class
        ) {
            val type = it.payload.type

            Logger.d("d") { "my type: $type" }
            Logger.d("d") { "payload: ${it.payload.payload}" }

            when (type) {
                ChatTypesNames.MemberTyping -> delegate?.onMemberTyping(it.payload.payload.memberId!!)
                ChatTypesNames.MessageCreated -> delegate?.onMessageCreated()


                

                ChatTypesNames.ConversationUpdated -> it.payload.payload.lastMessage?.let { message ->
                    Logger.d("d") { "message: $message" }
                    delegate?.receiveNewMessage(listOf(message))
                }


                ChatTypesNames.ConversationSynced -> {
                    it.payload.payload.lastSeenMessageIndex?.let { id ->
                        delegate?.updateTargetLastSeenMessage(id)
                    }
                }
            }
        }

        hubConnection!!.on(
            ChatMethodNames.ConversationUpdated(conversationId!!).value,
            ConversationsMyResponse::class,
            Unit::class
        ) {
            val type = it.payload.type

            Logger.d("d") { "upd type: $type" }
            Logger.d("d") { "payload: ${it.payload.payload}" }

            when (type) {
                ChatTypesNames.MemberTyping -> delegate?.onMemberTyping(it.payload.payload.memberId!!)
                ChatTypesNames.MessageCreated -> delegate?.onMessageCreated()
                ChatTypesNames.ConversationUpdated -> it.payload.payload.lastMessage?.let { message ->
                    Logger.d("d") { "message: $message" }
                    delegate?.receiveNewMessage(listOf(message))
                }

                ChatTypesNames.ConversationSynced -> {
                    it.payload.payload.lastSeenMessageIndex?.let { id ->
                        delegate?.updateTargetLastSeenMessage(id)
                    }
                }
            }
        }
    } catch (e: Exception) {
        Logger.d("d") { "ошибка из-за попытки повторно зарегистрировать слушатели при переподключении \n ошибка:$e" }
    }

    private suspend fun negotiateChat(
        data: NegotiateChatRequest,
    ): NegotiateChatResponse = client.post("https://chat.accelonline.io/hub/negotiate") {
        authHeader(data)

        parameter("negotiateVersion", data.negotiateVersion)
    }.body<NegotiateChatResponse>()

    suspend fun getMessages(
        data: GetMessagesRequest,
    ): List<MessageItem> {
        Logger.d("d") { "001" }
        Logger.d("d") { "7" }
        val response = hubConnection!!.invoke(
            ChatMethodNames.GetMessages.value,
            data.copy(conversationId = conversationId!!),
            GetMessagesResponse::class
        )
        Logger.d("d") { "8" }
        return response.body!!.items
    }

    suspend fun syncConversation(
        data: SyncConversationRequest,
    ) {
        hubConnection!!.invoke(
            ChatMethodNames.SyncConversation.value,
            data,
            Unit::class
        )
    }

    suspend fun close() {
        hubConnection?.stop()
        hubConnection = null
    }

    suspend fun sendMessage(message: SendMessageRequest): SendMessageResponse {
        Logger.d("d") { "sendMessage" }
        return hubConnection!!.invoke(
            ChatMethodNames.SendMessage.value,
            message.copy(conversationId = conversationId!!),
            SendMessageResponse::class
        )
    }
}
