package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common

import kotlinx.serialization.Serializable

@Serializable
data class School(
    val id: String,
    val ownerId: String,
    val owner: SchoolOwner? = null,
    val name: String,
    val description: String,
    val domain: String,
    val timezoneId: String?,
    val timezone: Timezone? = null,
    val currency: String,
    val number: Int,
    val billing: String?, // TODO: need to be fixed
    val partnership: Partnership,
    val gamification: Gamification,
    val countryId: String?,  // TODO: need to be fixed
    val country: Country? = null,
    val lang: String,
    val appIntegrations: List<String>? = null,
    val chatChannelIntegrations: List<ChatChannelIntegration>? = null,
    val allowCustomProductGatewaySettings: Boolean? = null,
)