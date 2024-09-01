package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryItem

import kotlinx.serialization.Serializable

@Serializable
class GetSchoolLibraryItemResponse(
    val items: List<SchoolLibraryItem>,
)

@Serializable
class ParentItem(
    val id: String,
)

@Serializable
class Progress(
    val id: String,
    val beginDate: String?,
)

@Serializable
data class SchoolLibraryItem(
    val id: String,
    val title: String,
    val type: String,
    val flowTimeDelayActive: Boolean,
//    val flowTimeDelay: Any?,
//    val flowTimeDelayCondition: Any?,
//    val flowTimeDelayMeasureType: Any?,
    val preventCopingAndSelectingText: Boolean,
    val allowComments: Boolean,
    val showComments: Boolean,
    val ratingEnabled: Boolean,
    val ratingCommentRequired: Boolean,
    val studentHasAccess: Boolean,
    val position: Int,
    val parentItem: ParentItem?,
    val progress: Progress?,
)