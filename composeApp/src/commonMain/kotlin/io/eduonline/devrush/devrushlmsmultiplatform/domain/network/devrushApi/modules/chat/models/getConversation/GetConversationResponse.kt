package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getConversation

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ResponseError
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Avatar
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common.ChatApiResponse
import kotlinx.serialization.Serializable

@Serializable
data class GetConversationResponse(
    override val success: Boolean,
    override val errors: List<ResponseError>,
    override val body: GetConversationResponseData?,
    override val resetToken: Boolean,
) : ChatApiResponse<GetConversationResponseData>



@Serializable
data class GetConversationResponseData(
    val id: String? = null,
    val title: String? = null,
    val memberCount: Int? = null,
    val isDraft: Boolean? = null,
    val readonly: Boolean? = null,
    val creatorUnsubscribed: Boolean? = null,
    val lastSeenMessageIndex: Int? = null,
    val targetLastSeenMessageIndex: Int? = null,
    val lastDeliveredMessageIndex: Int? = null,
    val lastActivityDate: String? = null,
    val type: String? = null,
    val state: String? = null,
    val channelType: String? = null,
    val creatorUnsubscribedReason: String? = null,
    val creatorLastActivityDate: String? = null,
    val channelColor: String? = null,
    val channelId: String? = null,
    val creatorUserExternalId: String? = null,
    val lastMessage: LastMessage? = null,
    val target: Target? = null,
    val messageSettings: MessageSettings? = null,
    val members: List<Member>? = null,
)

@Serializable
data class LastMessage(
    val id: String,
    val authorId: String,
    val index: Int,
    val text: String?,
    val plainText: String?,
    val callDurationInSec: Int? = null,
    val type: String,
    val renderMethod: String,
    val author: Author? = null,
    val reply: Reply? = null,
    val location: Location? = null,
    val template: Template? = null,
//    val attachments: List<Attachment>? = null,
    val reactions: List<Reaction>? = null,
    val buttons: List<Button>? = null,
    val softDeleted: Boolean,
    val hasLocation: Boolean,
    val hasAttachments: Boolean,
    val hasReactions: Boolean,
    val hasButtons: Boolean,
    val hasTemplate: Boolean,
    val sent: Boolean,
    val sentError: Boolean,
    val sentErrorCode: String? = null,
    val sentErrorMessage: String? = null,
    val deleted: Boolean,
    val deleting: Boolean,
    val deletedError: Boolean,
    val deletedErrorMessage: String? = null,
    val createdDate: String,
    val updatedDate: String,
    val softDeletedDate: String? = null
)

@Serializable
data class Author(
    val isActive: Boolean,
    val userId: String,
    val conversationId: String,
    val lastSeenMessageIndex: Int,
    val post: String? = null,
    val onlineStatus: String,
    val internalNumber: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val userName: String? = null,
    val phone: String? = null,
    val externalId: String? = null,
    val metadata: String? = null,
    val isSystem: Boolean,
    val createdDate: String? = null,
    val syncDate: String? = null,
    val lastSeenDate: String? = null,
    val channelType: String? = null,
    val adminId: String? = null,
    val admin: Admin? = null,
    val studentId: String? = null,
    val student: Student? = null,
    val callUserId: String? = null,
    val callUser: CallUser? = null,
    val avatarJson: String? = null,
    val files: List<File>? = null,
    val chatConversations: List<ChatConversation>? = null,
    val type: String? = null,
    val avatar: Avatar? = null,
    val schoolId: String? = null,
    val school: School? = null,
    val id: String? = null,
    val softDeleted: Boolean,
    val softDeletedDate: String? = null
)

@Serializable
data class Target(
    val isActive: Boolean,
    val userId: String,
    val conversationId: String,
    val lastSeenMessageIndex: Int,
    val post: String? = null,
    val onlineStatus: String? = null,
    val internalNumber: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val userName: String? = null,
    val phone: String? = null,
    val externalId: String? = null,
    val metadata: String? = null,
    val isSystem: Boolean,
    val createdDate: String? = null,
    val syncDate: String? = null,
    val lastSeenDate: String? = null,
    val channelType: String? = null,
    val adminId: String? = null,
    val admin: Admin? = null,
    val studentId: String? = null,
    val student: Student? = null,
    val callUserId: String? = null,
    val callUser: CallUser? = null,
    val avatarJson: String? = null,
    val files: List<File>? = null,
    val chatConversations: List<ChatConversation>? = null,
    val type: String? = null,
    val avatar: Avatar? = null,
    val schoolId: String? = null,
    val school: School? = null,
    val id: String? = null,
    val softDeleted: Boolean,
    val softDeletedDate: String? = null
)

@Serializable
data class MessageSettings(
    val canEdit: Boolean,
    val canDelete: Boolean,
    val canReply: Boolean,
    val maxLength: Int,
    val maxFileSize: Int,
    val modifyTimeout: Int,
    val responseTimeLimit: Int? = null,
    val forbiddenFileExtensions: List<String>
)

@Serializable
data class Member(
    val isActive: Boolean,
    val userId: String,
    val conversationId: String,
    val lastSeenMessageIndex: Int,
    val post: String? = null,
    val onlineStatus: String? = null,
    val internalNumber: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val userName: String? = null,
    val phone: String? = null,
    val externalId: String? = null,
    val metadata: String? = null,
    val isSystem: Boolean,
    val createdDate: String? = null,
    val syncDate: String? = null,
    val lastSeenDate: String? = null,
    val channelType: String? = null,
    val adminId: String? = null,
    val admin: Admin? = null,
    val studentId: String? = null,
    val student: Student? = null,
    val callUserId: String? = null,
    val callUser: CallUser? = null,
    val avatarJson: String? = null,
    val files: List<File>? = null,
    val chatConversations: List<ChatConversation>? = null,
    val type: String? = null,
    val avatar: Avatar? = null,
    val schoolId: String? = null,
    val school: School? = null,
    val id: String? = null,
    val softDeleted: Boolean,
    val softDeletedDate: String? = null
)

@Serializable
data class Admin(val id: String)
@Serializable
data class Student(val id: String)
@Serializable
data class CallUser(val id: String)
@Serializable
data class Avatar(val id: String, val cloudKey: String)
@Serializable
data class File(val id: String)
@Serializable
data class ChatConversation(val id: String)
@Serializable
data class Reply(val id: String)
@Serializable
data class Location(val id: String)
@Serializable
data class Template(val id: String?, val externalId: String?, val title: String?, val name: String?)
//@Serializable
//data class Attachment(val id: String)
@Serializable
data class Reaction(val id: String)
@Serializable
data class Button(val id: String)
@Serializable
data class School(val id: String)