package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class GetNotificationsResponse(
    val items: List<Notification>,
    val filter: NotificationFilter,
)

@Serializable
data class Notification(
    val id: String,
    val type: String,
    val payloadJson: String,
    val createdDate: String,
    val seenDate: String?,
    val studentId: String?,
    val adminId: String?,
    val adminTaskId: String?,
    val courseItemId: String?,
    val libraryItemId: String?,
    val orderId: String?,
    val orderPaymentId: String?,
    val scenarioId: String?,
    val chatConversationId: String?,
    val courseId: String?,
    val libraryId: String?,
    val courseItemTaskId: String?,
    val schoolContentItemCommentId: String?,
) {
    val payload: NotificationPayload
        get() = Json { ignoreUnknownKeys = true }.decodeFromString(payloadJson)
}

@Serializable
data class NotificationPayload(
    val id: String? = null,
    val type: String? = null,
    val comment: String? = null,
    val amount: Float? = null,
    val hasBonusTransaction: Boolean? = null,
    val hasPartnershipTransaction: Boolean? = null,
    val hasComment: Boolean? = null,
    val user: NotificationUser? = null,
    val courseName: String? = null,
    val courseItemName: String? = null,
    val status: String? = null,
    val scenarioName: String? = null,
    val message: String? = null,
)

@Serializable
data class NotificationUser(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phone: String?,
)

@Serializable
data class NotificationFilter(
    val id: String?,
    val excludeId: String?, // TODO: need to be fixed
    val subscriberAdminId: String?,
    val subscriberStudentId: String?,
    val adminId: String?,
    val studentId: String?,
    val adminTaskId: String?,
    val courseItemTaskId: String?,
    val courseItemCommentId: String?,
    val orderId: String?,
    val orderPaymentId: String?,
    val pipelineItemId: String?,
    val scenarioId: String?,
    val chatConversationId: String?,
    val read: String?, // TODO: need to be fixed
    val type: String?, // TODO: need to be fixed
    val types: List<String>?, // TODO: need to be fixed
    val chatConversationIds: List<String>?,
    val itemsTotal: Int,
    val take: Int,
    val skip: Int,
    val search: String?, // TODO: need to be fixed
    val sortName: String?, // TODO: need to be fixed
    val sortType: String?, // TODO: need to be fixed
    val useSort: Boolean,
    val useBaseFilter: Boolean,
    val useItemsTotal: Boolean,
    val softDeleted: Boolean,
    val ids: List<String>?,
)