package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models

sealed interface DetailCourseEvent {
    data object OpenCourseCertificate : DetailCourseEvent

    data class OpenLesson(val lessonId: String) : DetailCourseEvent

    data class GetCourse(
        val studentCourseId: String,
        val courseItemId: String,
        val imageCloudKey: String?,
        val retried: Boolean = false,
    ) : DetailCourseEvent

    data class GetLibrary(
        val libraryId: String,
        val imageCloudKey: String?,
        val retried: Boolean = false,
    ) : DetailCourseEvent

    data class SetPageType(val type: PageType) : DetailCourseEvent
}