package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest


data class GetSchoolContentItemsRequest(
    override val token: String,

    val sectionType: SectionType,
    val search: String = "",
    val courseType: CourseType = CourseType.All,
    val onlyAvailable: Boolean = true,
) : ApiAuthRequest

sealed class CourseType(val value: String) {
    data object Available : CourseType("available")
    data object Started : CourseType("started")
    data object All : CourseType("all")
}


sealed class SectionType(val value: String) {
    data object Course : SectionType("course")
    data object Library : SectionType("library")
}