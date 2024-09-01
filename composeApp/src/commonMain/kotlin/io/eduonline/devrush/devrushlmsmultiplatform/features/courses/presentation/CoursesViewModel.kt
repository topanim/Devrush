package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation

import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.ExpiredTokenException
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.components.filterRow.filterCell.FilterCellModel
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.Notification
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.notification.NotificationRepository
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain.GetCachedCoursesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain.GetRemoteCoursesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain.PushMarkAllAsReadUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models.CoursesAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models.CoursesEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models.CoursesLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models.CoursesViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CoursesViewModel(
    private val getCachedCoursesUseCase: GetCachedCoursesUseCase,
    private val getRemoteCoursesUseCase: GetRemoteCoursesUseCase,
    private val pushMarkAllAsReadUseCase: PushMarkAllAsReadUseCase,
) : BaseViewModel<CoursesViewState, CoursesAction, CoursesEvent>(
    initialState = CoursesViewState()
), KoinComponent {

    private val notificationRepository: NotificationRepository by inject()

    override fun obtainEvent(viewEvent: CoursesEvent) {
        when (viewEvent) {
            CoursesEvent.ClickBell -> viewAction = CoursesAction.PresentNotifications
            CoursesEvent.ClickMarkAll -> {
                viewAction = CoursesAction.CloseNotifications
                pushMarkAllAsRead()
            }

            is CoursesEvent.ClickItemFilter -> handleClickItemFilter(viewEvent.type)
            is CoursesEvent.ClickItemCourse -> viewAction = CoursesAction.PresentDetailCourse(
                viewEvent.studentCourseId, viewEvent.courseItemId, viewEvent.imageCloudKey
            )

            is CoursesEvent.GetCourse -> getCourses(viewEvent.retried)

            is CoursesEvent.ClickSearchRow -> viewAction = CoursesAction.PresentSearchScreen(
                viewEvent.courses
            )

            is CoursesEvent.Refresh -> {
                getCourses(true)
                viewState = viewState.copy(isRefresh = !viewState.isRefresh)
            }
        }
    }

    private fun pushMarkAllAsRead() = viewModelScope.launch {
        safeExecute(scope = viewModelScope, block = {
            pushMarkAllAsReadUseCase()
        }) {
            Logger.d { it.toString() }
        }
    }

    private fun refreshing(value: Boolean) {
        viewState = viewState.copy(isRefresh = value)
    }

    private fun getCourses(retried: Boolean) {
        Logger.d("d") { "get courses: retried: $retried" }
        safeExecute(
            scope = viewModelScope,
            block = {
                val notifications = notificationRepository.getAllFromServer()
                val cashedCourses = getCachedCoursesUseCase()
                if (cashedCourses.isNotEmpty())
                    withContext(Dispatchers.Main) { updateUI(cashedCourses, notifications) }

                if (viewState.isRefresh) updateFetchState(
                    CoursesLoadingState.Loading
                )
                val courses = getRemoteCoursesUseCase()
                withContext(Dispatchers.Main) { updateUI(courses, notifications) }
                refreshing(false)
            }
        ) {
            refreshing(false)
            updateFetchState(CoursesLoadingState.Error(it.toString()))
        }

    }

    private fun updateUI(courses: List<Course>, notifications: List<Notification>) {
        viewState = viewState.copy(
            allCourses = courses,
            notifications = notifications,
            fetchCourseInfoRequest = CoursesLoadingState.Success
        )
        updateState()
    }

    private fun updateFetchState(state: CoursesLoadingState) {
        viewState = viewState.copy(fetchCourseInfoRequest = state)
    }

    private fun updateState() {
        val filters =
            listOf("Все") + viewState.allCourses.flatMap { it.categories }.toSet().toList()
        val filterList = filters.map { FilterCellModel(it, it == viewState.currentChipSelected) }

        viewState = viewState.copy(
            startedCourses = viewState.allCourses
                .filter { it.studentCourse.progress > 0 && it.studentCourse.progress < 100 },
            filters = filterList,
            courses = if (viewState.currentChipSelected == "Все") viewState.allCourses
            else viewState.allCourses.filter { it.categories.contains(viewState.currentChipSelected) },
        )
    }

    private fun handleClickItemFilter(type: String) {
        viewState = viewState.copy(currentChipSelected = type)
        updateState()
    }
}