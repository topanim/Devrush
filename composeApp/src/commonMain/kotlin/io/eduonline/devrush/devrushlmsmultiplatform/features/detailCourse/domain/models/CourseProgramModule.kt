package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models

data class CourseProgramModule(
    var moduleNumber: Int = 0,
    val position: Int,
    val title: String?,
    var completed: Boolean,
    val lessons: MutableList<CourseLesson>,
    var available: Boolean = false,
)