package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseInfo

import kotlinx.serialization.Serializable

@Serializable
data class GetStudentCourseInfoResponse(
    val studentCourse: StudentCourse,
    val hasItemAccess: Boolean,
)

@Serializable
data class StudentCourse(
    val id: String,
    val beginDate: String,
    val endDate: String?,
    val state: String,
    val flowBeginDate: String?,
    val accessBeginDate: String,
    val accessEndDate: String?,
    val isSubscription: Boolean,
    val course: StudentCourseCourse,
    val currentCourseItem: CourseStudentItem,
    val feedback: String?,
    val progress: Float,
    val student: CourseStudent,
)

@Serializable
data class CourseStudent(
    val id: String,
)

@Serializable
data class CourseStudentItem(
    val id: String,
)

@Serializable
data class StudentCourseCourse(
    val id: String,
    val name: String,
    val cert: StudentCourseCourseCertificate?,
    val shortDescription: String,
    val dontAskFeedback: Boolean,
)

@Serializable
data class StudentCourseCourseCertificate(
    val id: String,
    val number: String,
    val image: CourseStudentCourseCertificateImage,
)

@Serializable
data class CourseStudentCourseCertificateImage(
    val cloudKey: String,
    val id: String,
)