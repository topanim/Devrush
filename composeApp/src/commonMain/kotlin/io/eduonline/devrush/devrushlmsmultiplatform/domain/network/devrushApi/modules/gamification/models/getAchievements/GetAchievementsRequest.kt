package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getAchievements

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.PaginationRequest
import kotlinx.serialization.Serializable

@Serializable
data class GetAchievementsRequest(
    override val token: String,

    override val take: Int = 10,
    override val skip: Int = 0,

    val softDeleted: Boolean = false,
    val useSort: Boolean = true,
    val useBaseFilter: Boolean = false,
    val useItemsTotal: Boolean = true,
    val showAll: Boolean = true,
) : ApiAuthRequest, PaginationRequest