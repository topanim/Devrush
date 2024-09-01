package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryInfo

import kotlinx.serialization.Serializable

@Serializable
class GetSchoolLibraryInfoResponse(
    val id: String,
    val title: String,
    val shortDescription: String,
    val flowBeginDate: String?,
)