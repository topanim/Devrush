package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.negotiateChat

import kotlinx.serialization.Serializable

@Serializable
data class NegotiateChatResponse(
    val negotiateVersion: Int,
    val connectionId: String,
    val connectionToken: String,
    val availableTransports: List<AvailableTransport>,
)

@Serializable
data class AvailableTransport(
    val transport: String,
    val transferFormats: List<String>,
)