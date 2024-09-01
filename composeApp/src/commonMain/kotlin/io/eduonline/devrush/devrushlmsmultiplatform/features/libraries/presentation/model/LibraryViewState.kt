package io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.Notification

data class LibraryViewState(
    val search: String = "",
    val library: List<Library> = emptyList(),
    val fetchCourseInfoRequest: LibraryLoadingState = LibraryLoadingState.Loading,
    val allLibrary: List<Library> = emptyList(),
    val notifications: List<Notification> = emptyList(),
    val isRefresh: Boolean = false,
)

sealed interface LibraryLoadingState {
    data object Init : LibraryLoadingState
    data object Loading : LibraryLoadingState
    data object Success : LibraryLoadingState
    data object Error : LibraryLoadingState
}