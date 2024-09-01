package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common

import kotlinx.serialization.Serializable

@Serializable
data class ChatChannelIntegration(
    val id: String,
    val name: String,
    val type: String,
    val canInitiateMessaging: Boolean,
)

