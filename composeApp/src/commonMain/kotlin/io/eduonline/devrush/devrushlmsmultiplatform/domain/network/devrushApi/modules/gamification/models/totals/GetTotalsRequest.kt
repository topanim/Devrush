package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.totals

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable


@Serializable
data class GetTotalsRequest(
    override val token: String,
) : ApiAuthRequest