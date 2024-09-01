package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourses

import kotlinx.serialization.Serializable

@Serializable
data class GetStudentCoursesResponse(
    val items: List<StudentCourse>,
)

@Serializable
data class StudentCourse(
    val id: String,
    val beginDate: String,
    val endDate: String?,
    val state: String,
    val flowBeginDate: String?,
    val accessBeginDate: String?,
    val accessEndDate: String,
    val isSubscription: String,
    val course: Course,
    val currentCourseItem: CurrentCourseItem,
    val feedback: String?, // TODO: fix this
    val progress: Float,
    val student: Student,
)

@Serializable
data class CurrentCourseItem(
    val id: String,
)

@Serializable
data class Course(
    val id: String,
    val name: String,
    val dontAskFeedback: Boolean,
)

@Serializable
data class Student(
    val id: String,
)