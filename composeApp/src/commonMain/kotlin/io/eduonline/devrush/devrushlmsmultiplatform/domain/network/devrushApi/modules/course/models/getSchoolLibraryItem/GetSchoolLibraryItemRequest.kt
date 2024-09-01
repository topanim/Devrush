package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryItem

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.PaginationRequest

class GetSchoolLibraryItemRequest(
    override val token: String,

    val libraryId: String,

    override val skip: Int = 0,
    override val take: Int = 25,

    val softDeleted: Boolean = false,
    val useSort: Boolean = true,
    val useBaseFilter: Boolean = false,
    val useItemsTotal: Boolean = true,
    val sortName: String = "position",
    val sortType: String = "ASC",
    val fields: String = "{id,title,type,flowTimeDelayActive,flowTimeDelay,flowTimeDelayCondition,flowTimeDelayMeasureType,preventCopingAndSelectingText,allowComments,showComments,ratingEnabled,ratingCommentRequired,studentHasAccess,position,parentItem{id,}progress{id,beginDate}}",
) : ApiAuthRequest, PaginationRequest