package io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library

sealed interface SearchEvent {
    data object CloseScreenClicked : SearchEvent
    data class GetSearchResults(val query: String, val retried: Boolean = false) : SearchEvent
    data class GetCourse(val courses: List<Course>) : SearchEvent
    data class GetLibrary(val libraries: List<Library>) : SearchEvent

    class ClickItemCourse(
        val studentCourseId: String,
        val courseItemId: String,
        val imageCloudKey: String,
    ) : SearchEvent

    class ClickItemLibrary(
        val libraryId: String,
        val imageCloudKey: String?,
    ) : SearchEvent
}