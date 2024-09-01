package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models

data class CourseLesson(
    val id: String,
    val position: Int,
    val name: String?,
    val started: Boolean,
    val completed: Boolean,
)