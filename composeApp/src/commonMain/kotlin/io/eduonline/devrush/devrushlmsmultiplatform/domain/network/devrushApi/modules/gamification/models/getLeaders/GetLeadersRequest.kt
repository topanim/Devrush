package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getLeaders

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.PaginationRequest
import kotlinx.serialization.Serializable


@Serializable
data class GetLeadersRequest(
    override val token: String,

    override val skip: Int = 0,
    override val take: Int = 25,
    val softDeleted: Boolean = false,
    val useSort: Boolean = true,
    val useBaseFilter: Boolean = true,
    val useItemsTotal: Boolean = true,
) : ApiAuthRequest, PaginationRequest