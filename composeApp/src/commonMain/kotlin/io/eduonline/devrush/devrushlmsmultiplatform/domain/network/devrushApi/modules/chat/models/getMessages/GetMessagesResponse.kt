package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ResponseError
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Avatar
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.ChannelType
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.ChatApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.OnlineStatus
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.UserType
import kotlinx.serialization.Serializable

@Serializable
data class GetMessagesResponse(
    override val success: Boolean,
    override val errors: List<ResponseError>,
    override val body: GetMessagesResponseData?,
    override val resetToken: Boolean,
) : ChatApiResponse<GetMessagesResponseData>

@Serializable
data class GetMessagesResponseData(
    val items: List<MessageItem>,
)

@Serializable
data class MessageItem(
    val id: String,
    val authorId: String,
    val index: Int,
    val text: String?,
    val plainText: String?,
//    val callDurationInSec: Any?,
    val type: String,
    val renderMethod: String,
    val author: MessageAuthor?,
    val reply: MessageItem?=null,
//    val location: Any?,
    val template: MessageItemTemplate?,
    val attachments: List<Attachment>?,
//    val reactions: Any?,
//    val buttons: Any?,
    val softDeleted: Boolean,
    val hasLocation: Boolean,
    val hasAttachments: Boolean,
    val hasReactions: Boolean,
    val hasButtons: Boolean,
    val hasTemplate: Boolean,
    val sent: Boolean,
    val sentError: Boolean,
    val sentErrorCode: Int?,
    val sentErrorMessage: String?,
    val deleted: Boolean,
    val deleting: Boolean,
    val deletedError: Boolean,
    val deletedErrorMessage: String?,
    val createdDate: String,
    val updatedDate: String,
//val softDeletedDate: Any?
)

@Serializable
data class MessageAuthor(
    val isActive: Boolean,
    val userId: String,
    val conversationId: String,
    val lastSeenMessageIndex: Int,
    val post: String,
    val onlineStatus: OnlineStatus,
    val internalNumber: Int?,
    val email: String,
    val firstName: String,
    val lastName: String?,
    val userName: String?,
    val phone: String?,
    val externalId: String?,
//    val metadata: Any?,
    val isSystem: Boolean,
    val createdDate: String?,
    val syncDate: String?,
    val lastSeenDate: String,
    val channelType: ChannelType,
    val adminId: String?,
//    val admin: Any?,
    val studentId: String?,
//    val student: Any?,
    val callUserId: String?,
//    val callUser: Any?,
    val avatarJson: String,
    val files: List<String>,
    val chatConversations: List<String>,
    val type: UserType,
    val avatar: Avatar,
    val schoolId: String,
//    val school: Any?,
    val id: String,
    val softDeleted: Boolean,
    val softDeletedDate: String?,
)


@Serializable
data class MessageItemTemplate(
    val id: String?,
    val externalId: String?,
    val title: String?,
    val name: String?,
)

@Serializable
data class Attachment(
    val channelType: String?,
    val type: String?,
    val id: String?,
    val cloudKey: String?,
    val name: String?,
    val mimeType: String?,
    val extension: String?,
    val size: String?,
    val url: String?,
    val thumbnailUrl: String?,
    val permalink: String?,
    val additionalData: String?,
    val hasDownloadUrl: Boolean?,
    val hasPermalink: Boolean?,
    val hasId: Boolean?,
    val fullName: String?
)