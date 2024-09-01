package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSubscriptions

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.PaginationRequest
import kotlinx.serialization.Serializable


@Serializable
data class GetSubscriptionsRequest(
    override val token: String,

    override val skip: Int = 0,
    override val take: Int = 10,
    val softDeleted: Boolean = false,
    val useSort: Boolean = true,
    val useBaseFilter: Boolean = true,
    val useItemsTotal: Boolean = true,
    val fields: String = "{id,beginDate,endDate,creditEndDate,type,state,product{id,name,subsPrice,subsDuration,subsDurationType,image{id,name,cloudKey,extension,size,type}}}",
) : ApiAuthRequest, PaginationRequest