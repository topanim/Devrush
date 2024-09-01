package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems

import kotlinx.serialization.Serializable

@Serializable
data class GetSchoolContentItemsResponse(
    val items: List<SchoolContentItem>,
)

@Serializable
data class SchoolContentItem(
    val id: String,
    val title: String?,
    val description: String?,
    val type: String,
    val coverCloudKey: String?,
    val parentId: String?,
    val position: Int,
    val course: Course?,
    val library: Library?,
//    val children: List<Any>
)

@Serializable
data class Library(
    val title: String?,
    val shortDescription: String,
    val freeAccess: Boolean,
    val primaryImageCloudKey: String?,
    val studentHasAccess: Boolean,
    val studentAccessDate: String?,
    val flowBeginDate: String?,
    val categories: List<String>,
    val id: String,
    val softDeleted: Boolean,
    val softDeletedDate: String?,
)

@Serializable
data class Course(
    val title: String?,
    val shortDescription: String,
    val primaryImageCloudKey: String?,
    val freeAccess: Boolean,
    val studentHasAccess: Boolean,
    val isCompleteAnyCourseRequirement: Boolean,
    val studentCourse: StudentCourse,
    val studentAccessDate: String?,
    val categories: List<String>,
//    val requirements: Any?,
//    val cert: Any?,
    val id: String,
    val softDeleted: Boolean,
    val softDeletedDate: String?,

    )

@Serializable
data class StudentCourse(
    val id: String,
    val progress: Float,
    val currentCourseItem: CurrentCourseItem,
)

@Serializable
data class CurrentCourseItem(
    val id: String,
    val title: String?,
    val position: Int,
)

@Serializable
data class CurrentLibraryItem(
    val id: String,
    val title: String?,

    )