package io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library

sealed interface LibraryAction {
    data object RetryGetCourse : LibraryAction

    data object PresentNotification : LibraryAction
    data object CloseNotification : LibraryAction
    class PresentSearchScreen(val libraries: List<Library>) : LibraryAction
    class PresentDetailScreen(
        val libraryId: String,
        val imageCloudKey: String?,
    ) : LibraryAction
}