package io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library

sealed interface LibraryEvent {
    data class GetCourse(val retried: Boolean = false) : LibraryEvent
    data object ClickMarkAll : LibraryEvent
    data object ClickNotification : LibraryEvent
    class ClickSearchRow(val libraries: List<Library>) : LibraryEvent
    class ClickItemLibrary(
        val libraryId: String,
        val imageCloudKey: String?,
    ) : LibraryEvent

    class RefreshPage(val isRefresh: Boolean) : LibraryEvent
}