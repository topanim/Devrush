package io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation

import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.ExpiredTokenException
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.Notification
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.notification.NotificationRepository
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain.PushMarkAllAsReadUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.domain.GetCachedLibrariesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.domain.GetRemoteLibrariesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model.LibraryAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model.LibraryEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model.LibraryLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model.LibraryViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LibraryViewModel(
    private val getCachedLibrariesUseCase: GetCachedLibrariesUseCase,
    private val getRemoteLibrariesUseCase: GetRemoteLibrariesUseCase,
    private val pushMarkAllAsReadUseCase: PushMarkAllAsReadUseCase,
) : BaseViewModel<LibraryViewState, LibraryAction, LibraryEvent>(
    LibraryViewState()
), KoinComponent {
    private val notificationRepository: NotificationRepository by inject()

    override fun obtainEvent(viewEvent: LibraryEvent) {
        when (viewEvent) {
            is LibraryEvent.GetCourse -> getCourses(viewEvent.retried)
            LibraryEvent.ClickNotification -> viewAction = LibraryAction.PresentNotification
            is LibraryEvent.ClickItemLibrary -> viewAction = LibraryAction.PresentDetailScreen(
                viewEvent.libraryId,
                viewEvent.imageCloudKey
            )

            is LibraryEvent.ClickSearchRow -> viewAction = LibraryAction.PresentSearchScreen(
                viewEvent.libraries
            )

            is LibraryEvent.RefreshPage -> {
                getCourses(true)
                viewState = viewState.copy(isRefresh = !viewState.isRefresh)
            }

            LibraryEvent.ClickMarkAll -> {
                viewAction = LibraryAction.CloseNotification
                pushMarkAllAsRead()
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

    private fun getCourses(retried: Boolean) {
        safeExecute(
            scope = viewModelScope,
            block = {
                val cashedLibrary = getCachedLibrariesUseCase()
                val notifications = notificationRepository.getAllFromServer()
                if (cashedLibrary.isNotEmpty())
                    withContext(Dispatchers.Main) { updateUI(cashedLibrary, notifications) }

                if (viewState.isRefresh) updateFetchState(
                    LibraryLoadingState.Loading
                )
                val remoteLibraries = getRemoteLibrariesUseCase()
                withContext(Dispatchers.Main) { updateUI(remoteLibraries, notifications) }
                refresh(false)
            }
        ) {
            refresh(false)
            if (it is ExpiredTokenException && !retried) {
                getCourses(retried)
            } else updateFetchState(LibraryLoadingState.Error)
        }
    }

    private fun updateUI(libraries: List<Library>, notifications: List<Notification>) {
        viewState = viewState.copy(
            allLibrary = libraries,
            notifications = notifications,
            fetchCourseInfoRequest = LibraryLoadingState.Success
        )
    }

    private fun refresh(refresh: Boolean) {
        viewState = viewState.copy(isRefresh = refresh)
    }

    private fun updateFetchState(state: LibraryLoadingState) {
        viewState = viewState.copy(fetchCourseInfoRequest = state)
    }

}