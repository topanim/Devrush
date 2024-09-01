package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getLicenses

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.PaginationRequest
import kotlinx.serialization.Serializable


@Serializable
data class GetLicensesRequest(
    override val token: String,

    val isExpired: Boolean,
    override val skip: Int = 0,
    override val take: Int = 25,
    val softDeleted: Boolean = false,
    val useSort: Boolean = true,
    val useBaseFilter: Boolean = true,
    val useItemsTotal: Boolean = true,

    val fields: String = "{id,beginDate,endDate,type,coursePlan{id,name,softDeleted,course{id,name,softDeleted}},course{id,name,softDeleted,primaryImage{id,file{id,name,cloudKey,extension,size,type}}},libraryAccess{library{id,name,softDeleted,primaryImage{id,file{id,name,cloudKey,extension,size,type}}}}}",
) : ApiAuthRequest, PaginationRequest