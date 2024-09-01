package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseInfo

sealed class DetailCourseLoadingState {
    data object Loading : DetailCourseLoadingState()
    data class Success(val course: CourseInfo) : DetailCourseLoadingState()
    data class Error(val text: String = "") : DetailCourseLoadingState()
}

data class DetailCourseState(
    val fetchCourseInfoRequest: DetailCourseLoadingState = DetailCourseLoadingState.Loading,
    val pageType: PageType = PageType.NON,
)

enum class PageType {
    CURSES, LIBRARY, NON
}