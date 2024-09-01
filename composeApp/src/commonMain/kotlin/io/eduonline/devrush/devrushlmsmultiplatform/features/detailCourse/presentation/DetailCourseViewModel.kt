package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation

import androidx.lifecycle.viewModelScope
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.GetCourseInfoUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.GetLibraryInfoUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseState
import org.koin.core.component.KoinComponent

class DetailCourseViewModel(
    private val getCourseInfoUseCase: GetCourseInfoUseCase,
    private val getLibraryInfoUseCase: GetLibraryInfoUseCase,
) : BaseViewModel<DetailCourseState, DetailCourseAction, DetailCourseEvent>(
    DetailCourseState()
), KoinComponent {
    override fun obtainEvent(viewEvent: DetailCourseEvent) = when (viewEvent) {
        is DetailCourseEvent.GetCourse -> getCourseInfo(
            viewEvent.studentCourseId,
            viewEvent.imageCloudKey
        )

        is DetailCourseEvent.GetLibrary -> getLibraryInfo(
            viewEvent.libraryId,
            viewEvent.imageCloudKey
        )

        is DetailCourseEvent.OpenLesson -> viewAction =
            DetailCourseAction.OpenLesson(viewEvent.lessonId)

        is DetailCourseEvent.SetPageType -> viewState =
            viewState.copy(pageType = viewEvent.type)

        DetailCourseEvent.OpenCourseCertificate -> viewAction =
            DetailCourseAction.OpenCourseCertificate
    }

    private fun updateFetchState(state: DetailCourseLoadingState) {
        viewState = viewState.copy(fetchCourseInfoRequest = state)
    }

    private fun processError(message: String) {
        viewState = viewState.copy(
            fetchCourseInfoRequest = DetailCourseLoadingState.Error(message)
        )
    }

    private fun getCourseInfo(
        studentCourseId: String,
        imageCloudKey: String?,
    ) {
        updateFetchState(DetailCourseLoadingState.Loading)

        safeExecute(
            scope = viewModelScope,
            block = {
                val courseInfo = getCourseInfoUseCase(studentCourseId)
                    .copy(imageKey = imageCloudKey ?: "")

                updateFetchState(DetailCourseLoadingState.Success(courseInfo))
            }
        ) {
            processError(it.message ?: "")
        }
    }

    private fun getLibraryInfo(
        libraryId: String,
        imageCloudKey: String?,
    ) {
        updateFetchState(DetailCourseLoadingState.Loading)

        safeExecute(
            scope = viewModelScope,
            block = {
                val libraryInfo = getLibraryInfoUseCase(libraryId)
                    .copy(imageKey = imageCloudKey ?: "")
                updateFetchState(DetailCourseLoadingState.Success(libraryInfo))
            }
        ) {
            processError(it.message ?: "")
        }
    }
}