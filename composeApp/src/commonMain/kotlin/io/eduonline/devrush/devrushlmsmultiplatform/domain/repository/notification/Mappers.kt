package io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.notification

import io.eduonline.devrush.devrushlmsmultiplatform.database.models.NotificationDBO
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.Notification

internal fun Notification.toNotificationDBO() = NotificationDBO(
    id = id,
    type = type,
    payloadJson = payloadJson,
    createdDate = createdDate,
    seenDate = seenDate,
    studentId = studentId,
    adminTaskId = adminTaskId,
    courseItemId = courseItemId,
    libraryItemId = libraryItemId,
    adminId = adminId,
    orderId = orderId,
    orderPaymentId = orderPaymentId,
    scenarioId = scenarioId,
    chatConversationId = chatConversationId,
    courseId = courseId,
    libraryId = libraryId,
    courseItemTaskId = courseItemTaskId,
    schoolContentItemCommentId = schoolContentItemCommentId
)

internal fun NotificationDBO.toNotification() = Notification(
    id = id,
    type = type,
    payloadJson = payloadJson,
    createdDate = createdDate,
    seenDate = seenDate,
    studentId = studentId,
    adminTaskId = adminTaskId,
    courseItemId = courseItemId,
    libraryItemId = libraryItemId,
    adminId = adminId,
    orderId = orderId,
    orderPaymentId = orderPaymentId,
    scenarioId = scenarioId,
    chatConversationId = chatConversationId,
    courseId = courseId,
    libraryId = libraryId,
    courseItemTaskId = courseItemTaskId,
    schoolContentItemCommentId = schoolContentItemCommentId
)