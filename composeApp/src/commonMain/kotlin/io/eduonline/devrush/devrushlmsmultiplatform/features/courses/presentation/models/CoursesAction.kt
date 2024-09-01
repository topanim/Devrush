package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course

sealed interface CoursesAction {
    data object RetryGetCourse : CoursesAction

    data object PresentNotifications : CoursesAction
    data object CloseNotifications : CoursesAction
    class PresentSearchScreen(val courses: List<Course>) : CoursesAction
    class PresentDetailCourse(
        val studentCourseId: String,
        val courseItemId: String,
        val imageCloudKey: String,
    ) : CoursesAction
}