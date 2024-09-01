package io.eduonline.devrush.devrushlmsmultiplatform.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification")
data class NotificationDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("payloadJson") val payloadJson: String,
    @ColumnInfo("createdDate") val createdDate: String,
    @ColumnInfo("seenDate") val seenDate: String?,
    @ColumnInfo("studentId") val studentId: String?,
    @ColumnInfo("adminId") val adminId: String?,
    @ColumnInfo("adminTaskId") val adminTaskId: String?,
    @ColumnInfo("courseItemId") val courseItemId: String?,
    @ColumnInfo("libraryItemId") val libraryItemId: String?,
    @ColumnInfo("orderId") val orderId: String?,
    @ColumnInfo("orderPaymentId") val orderPaymentId: String?,
    @ColumnInfo("scenarioId") val scenarioId: String?,
    @ColumnInfo("chatConversationId") val chatConversationId: String?,
    @ColumnInfo("courseId") val courseId: String?,
    @ColumnInfo("libraryId") val libraryId: String?,
    @ColumnInfo("courseItemTaskId") val courseItemTaskId: String?,
    @ColumnInfo("schoolContentItemCommentId") val schoolContentItemCommentId: String?,
)