package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification


import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.HEADER_REFERER
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.authHeader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.GetNotificationsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.GetNotificationsResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.markAllAsRead.MarkAllAsReadRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.markAllAsRead.MarkAllAsReadResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post

class NotificationModule(
    override val client: HttpClient,
    override val domain: String,
    override val path: String,
) : ApiModule {
    private val modulePath = "$domain/$path/$MODULE"

    companion object {
        const val MODULE = "notification"
        const val GET_NOTIFICATIONS_REFERER = "https://devrush.eduonline.io/"
    }

    suspend fun getNotifications(
        data: GetNotificationsRequest,
    ) = client.get(modulePath) {
        header(HEADER_REFERER, GET_NOTIFICATIONS_REFERER)
        authHeader(data)

        parameter("skip", data.skip)
        parameter("take", data.take)
        parameter("softDeleted", data.softDeleted)
        parameter("useSort", data.useSort)
        parameter("useBaseFilter", data.useBaseFilter)
        parameter("useItemsTotal", data.useItemsTotal)
        parameter("fields", data.fields)
    }.body<ApiResponse<GetNotificationsResponse>>()

    suspend fun markAllAsRead(
        data: MarkAllAsReadRequest,
    ) = client.post("$modulePath/markAllAsRead") {
        authHeader(data)
    }.body<ApiResponse<MarkAllAsReadResponse>>()
}