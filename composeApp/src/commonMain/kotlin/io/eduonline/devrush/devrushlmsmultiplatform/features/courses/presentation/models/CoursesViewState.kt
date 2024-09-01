package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.components.filterRow.filterCell.FilterCellModel
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.Notification

data class CoursesViewState(
    val fetchCourseInfoRequest: CoursesLoadingState = CoursesLoadingState.Loading,
    val notifications: List<Notification> = emptyList(),
    val startedCourses: List<Course> = emptyList(),
    val filters: List<FilterCellModel> = emptyList(),
    val courses: List<Course> = emptyList(),
    val allCourses: List<Course> = emptyList(),
    val currentChipSelected: String = "Все",
    val isRefresh: Boolean = false,
)

sealed interface CoursesLoadingState {
    data object Init : CoursesLoadingState
    data object Loading : CoursesLoadingState
    data object Success : CoursesLoadingState
    data class Error(val message: String) : CoursesLoadingState
}
