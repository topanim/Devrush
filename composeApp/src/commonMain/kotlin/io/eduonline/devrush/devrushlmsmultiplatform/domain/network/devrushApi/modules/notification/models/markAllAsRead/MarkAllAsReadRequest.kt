package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.markAllAsRead

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable


@Serializable
data class MarkAllAsReadRequest(
    override val token: String,
) : ApiAuthRequest