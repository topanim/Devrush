package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models

sealed interface DetailCourseAction {
    data object OpenCourseCertificate : DetailCourseAction

    data class RetryGetCourse(
        val studentCourseId: String,
        val courseItemId: String,
        val imageCloudKey: String?,
    ) : DetailCourseAction

    data class RetryGetLibrary(
        val libraryId: String,
        val imageCloudKey: String?,
    ) : DetailCourseAction


    data class OpenLesson(val lessonId: String) : DetailCourseAction
}