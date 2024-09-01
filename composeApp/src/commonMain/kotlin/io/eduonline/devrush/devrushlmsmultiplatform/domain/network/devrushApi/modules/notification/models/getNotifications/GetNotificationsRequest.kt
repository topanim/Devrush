package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest


class GetNotificationsRequest(
    override val token: String,

    override val skip: Int = 0,
    override val take: Int = 25,
    override val softDeleted: Boolean = false,
    override val useSort: Boolean = true,
    override val useBaseFilter: Boolean = true,
    override val useItemsTotal: Boolean = true,

    val fields: String = "{id,type,payloadJson,createdDate,seenDate,adminId,studentId,adminTaskId,courseItemId,libraryItemId,orderId,orderPaymentId,scenarioId,chatConversationId,courseId,libraryId,courseItemTaskId,schoolContentItemCommentId,}",
) : ApiAuthRequest, ApiRequestFilter

interface ApiRequestFilter {
    val skip: Int
    val take: Int
    val softDeleted: Boolean
    val useSort: Boolean
    val useBaseFilter: Boolean
    val useItemsTotal: Boolean
//    val read: Boolean
}