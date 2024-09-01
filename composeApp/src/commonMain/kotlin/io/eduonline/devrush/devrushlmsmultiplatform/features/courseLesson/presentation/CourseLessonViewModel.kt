package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation

import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.domain.DownloadFileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.domain.GetCourseItemFilesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.LessonRequestState
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonState
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

class CourseLessonViewModel(
    private val downloadFileUseCase: DownloadFileUseCase,
    private val getCourseItemFilesUseCase: GetCourseItemFilesUseCase,
) : BaseViewModel<CourseLessonState, CourseLessonAction, CourseLessonEvent>(
    CourseLessonState()
) {
    override fun obtainEvent(viewEvent: CourseLessonEvent) {
        when (viewEvent) {
            CourseLessonEvent.CloseBottomSheet -> setBottomSheetState(false)
            CourseLessonEvent.OpenBottomSheet -> setBottomSheetState(true)
            is CourseLessonEvent.LoadFile -> loadFile(
                viewEvent.id, viewEvent.name, viewEvent.extension
            )
            is CourseLessonEvent.FetchFiles -> fetchFiles(
                viewEvent.courseId, viewEvent.lessonId
            )
        }
    }

    private fun setBottomSheetState(state: Boolean) {
        viewState = viewState.copy(showFilesBottomSheet = state)
    }

    private fun fetchFiles(courseId: String, lessonId: String) {
        loadFilesState(LessonRequestState.Loading)
        safeExecute(
            scope = viewModelScope,
            block = {
                val response = getCourseItemFilesUseCase(courseId, lessonId)
                withContext(Main) {
                    viewState = viewState.copy(
                        files = response,
                        filesFetchState = LessonRequestState.Success
                    )
                }

            }
        ) { loadFilesState(LessonRequestState.Error) }
    }

    private fun loadFile(id: String, name: String, extension: String) {
        loadFileState(id, LessonRequestState.Loading)
        viewAction = CourseLessonAction.FileLoading(name)
        safeExecute(
            scope = viewModelScope,
            block = {
                downloadFileUseCase(id, name, extension)
                loadFileState(id, LessonRequestState.Success)
                viewAction = CourseLessonAction.FileLoaded(name)
            }
        ) {
            viewAction = CourseLessonAction.FileNotLoaded(name)
            Logger.d("d") { it.message.toString() }
            loadFileState(id, LessonRequestState.Error)
        }

    }

    private fun loadFilesState(state: LessonRequestState) {
        viewState = viewState.copy(filesFetchState = state)
    }

    private fun loadFileState(id: String, state: LessonRequestState) {
        viewState.files.find { it.id == id }?.downloadingState = state
    }
}