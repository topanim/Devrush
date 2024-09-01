package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.conversationsMy

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Avatar
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.ChannelType
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.ChatTypesNames
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.OnlineStatus
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.PayloadPayloadState
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.PayloadPayloadType
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.TargetType
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.MessageItem
import kotlinx.serialization.Serializable

@Serializable
data class ConversationsMyResponse(
    val payload: Payload,
//    val caller: Any?
)

@Serializable
data class Payload(
    val instanceId: String,
    val type: ChatTypesNames,
    val payload: PayloadPayload,
    val targetType: TargetType,
    val schoolId: String,
    val userId: String,
    val excludeUserId: String?,
    val excludedUserIds: List<String>,
//    val excludedConnectionIds: Any?,
//    val excludedCurrentConnectionId: Any?,
//    val conversationId: Any?,
)

@Serializable
data class PayloadPayload(
    val id: String? = null,
    val memberId: String? = null,
    val isDraft: Boolean? = null,
    val title: String? = null,
    val memberCount: Int? = null,
    val readonly: Boolean? = null,
    val creatorUnsubscribed: Boolean? = null,
    //        val creatorUnsubscribedReason: Any?,
    val lastSeenMessageIndex: Int? = null,
    val lastDeliveredMessageIndex: Int? = null,
    val lastActivityDate: String? = null,
//    val creatorLastActivityDate: Any?,
    val type: PayloadPayloadType? = null,
    val state: PayloadPayloadState? = null,
    val channelType: ChannelType? = null,
    val lastMessage: MessageItem? = null,
    val target: Target? = null,

//    isDraft, title, memberCount, readonly, creatorUnsubscribed, lastDeliveredMessageIndex, lastActivityDate, type, state, channelType, lastMessage, target
)

@Serializable
data class Target(
    val isActive: Boolean,
    val userId: String,
    val conversationId: String,
    val lastSeenMessageIndex: Int,
//        val post: Any?,
    val onlineStatus: OnlineStatus,
    val internalNumber: Int?,
    val email: String,
    val firstName: String,
    val lastName: String,
//        val userName: Any?,
    val phone: String,
    val externalId: String?,
//        val metadata: Any?,
    val isSystem: Boolean,
//        val createdDate: Any?,
    val syncDate: String?,
    val lastSeenDate: String?,
    val channelType: ChannelType,
    val adminId: String?,
//        val admin: Any?,
    val studentId: String,
//        val student: Any?,
    val callUserId: String?,
//        val callUser: Any?,
    val avatarJson: String,
    val files: List<String>,
    val chatConversations: List<String>,
    val type: TargetType,
    val avatar: Avatar,
    val schoolId: String,
//        val school: Any?,
    val id: String,
    val softDeleted: Boolean,
//        val softDeletedDate: Any?
)

