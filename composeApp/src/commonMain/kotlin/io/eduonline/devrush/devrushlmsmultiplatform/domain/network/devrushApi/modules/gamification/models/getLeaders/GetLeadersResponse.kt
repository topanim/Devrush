package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getLeaders


import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Leader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.LeadersFilter
import kotlinx.serialization.Serializable

@Serializable
class GetLeadersResponse(
    val filter: LeadersFilter,
    val items: List<Leader>,
)