package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model

sealed interface CourseLessonEvent {
    data object OpenBottomSheet : CourseLessonEvent
    data object CloseBottomSheet : CourseLessonEvent
    data class FetchFiles(val courseId: String, val lessonId: String) : CourseLessonEvent
    data class LoadFile(val id: String, val name: String, val extension: String) : CourseLessonEvent
}