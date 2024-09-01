package io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model

sealed interface SearchAction {
    data object CloseScreen : SearchAction

    data class RetryGetSearchResults(val query: String) : SearchAction

    class PresentDetailCourse(
        val studentCourseId: String,
        val courseItemId: String,
        val imageCloudKey: String,
    ) : SearchAction

    class PresentDetailLibrary(
        val libraryId: String,
        val imageCloudKey: String?,
    ) : SearchAction
}