package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.totals

import kotlinx.serialization.Serializable

@Serializable
data class GetTotalsResponse(
    val items: List<GamificationTotal>,
)

@Serializable
data class GamificationTotal(
    val value: Float,
    val type: String,
    val valueType: String,
)

