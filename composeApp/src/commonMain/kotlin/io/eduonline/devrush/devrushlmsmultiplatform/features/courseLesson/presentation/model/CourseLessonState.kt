package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.ui.components.LessonFile


sealed interface LessonRequestState {
    data object Idle : LessonRequestState
    data object Loading : LessonRequestState
    data object Success : LessonRequestState
    data object Error : LessonRequestState
}


data class CourseLessonState(
    val showFilesBottomSheet: Boolean = false,
    val files: List<LessonFile> = emptyList(),
    val filesFetchState: LessonRequestState = LessonRequestState.Loading,
    val fileDownloadState: LessonRequestState = LessonRequestState.Idle
)