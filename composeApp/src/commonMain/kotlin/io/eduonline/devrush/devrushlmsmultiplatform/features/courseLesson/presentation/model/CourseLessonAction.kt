package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model

sealed interface CourseLessonAction {
    data class FileLoading(val name: String) : CourseLessonAction
    data class FileLoaded(val name: String) : CourseLessonAction
    data class FileNotLoaded(val name: String) : CourseLessonAction
}