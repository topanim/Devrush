package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course

sealed interface CoursesEvent {
    data object ClickBell : CoursesEvent
    data object ClickMarkAll : CoursesEvent
    data class GetCourse(val retried: Boolean = false) : CoursesEvent
    class ClickSearchRow(val courses: List<Course>) : CoursesEvent
    class ClickItemFilter(val type: String) : CoursesEvent
    class ClickItemCourse(
        val studentCourseId: String,
        val courseItemId: String,
        val imageCloudKey: String,
    ) : CoursesEvent

    class Refresh(val refresh: Boolean) : CoursesEvent
}