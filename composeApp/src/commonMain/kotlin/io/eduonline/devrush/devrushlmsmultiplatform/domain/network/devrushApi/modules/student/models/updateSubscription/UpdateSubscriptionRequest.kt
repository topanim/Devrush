package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.updateSubscription

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable

data class UpdateSubscriptionRequest(
    override val token: String,
    val data: UpdateSubscriptionRequestData,
) : ApiAuthRequest

@Serializable
data class UpdateSubscriptionRequestData(
    val id: String,
    val state: SubscriptionState,
)


@Serializable
class SubscriptionState(val value: String) {
    companion object {
        val Active = SubscriptionState("active")
        val Paused = SubscriptionState("paused")
        val Expired = SubscriptionState("expired")
    }
}