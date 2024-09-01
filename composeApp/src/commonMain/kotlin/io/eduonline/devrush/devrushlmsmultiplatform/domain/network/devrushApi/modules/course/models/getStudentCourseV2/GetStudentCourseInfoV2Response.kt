package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseV2

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getLicenses.PrimaryImage
import kotlinx.serialization.Serializable

@Serializable
data class GetStudentCourseInfoV2Response(
    val items: List<GetStudentCourseInfoV2Data>,
)

@Serializable
data class GetStudentCourseInfoV2Data(
    val id: String,
    val beginDate: String?,
    val endDate: String?,
    val state: String,
    val flowBeginDate: String?,
    val accessBeginDate: String?,
    val accessEndDate: String?,
    val isSubscription: Boolean,
    val course: CourseInfoCourse,
    val currentCourseItem: CurrentCourseItemId,
//val feedback: Any?,
    val progress: Float,
    val student: StudentId,
)

@Serializable
data class StudentId(
    val id: String,
)

@Serializable
data class CurrentCourseItemId(
    val id: String,
)

@Serializable
data class CourseInfoCourse(
    val id: String,
    val name: String,
    val shortDescription: String,
    val freeAccess: Boolean,
    val dontAskFeedback: Boolean,
    val primaryImage: PrimaryImage?,
)