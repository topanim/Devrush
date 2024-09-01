package io.eduonline.devrush.devrushlmsmultiplatform.features.libraries

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.widget_icon
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.NotificationRow
import io.eduonline.devrush.devrushlmsmultiplatform.components.PlayingCat
import io.eduonline.devrush.devrushlmsmultiplatform.components.PullToRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui.views.NotificationBottomSheets
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.LibraryViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model.LibraryAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model.LibraryEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model.LibraryLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.ui.LibraryView
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject

object LibrariesScreen : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(vectorResource(Res.drawable.widget_icon))
            val title = DevRushTheme.strings.bottomLibraries

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = koinInject<LibraryViewModel>()
        val navigator = LocalNavigator.currentOrThrow.parent!!
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)

        var showBottomSheet by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            viewModel.obtainEvent(LibraryEvent.GetCourse())
        }

        PullToRefresh(
            content = {
                Column {
                    when (viewState.fetchCourseInfoRequest) {
                        LibraryLoadingState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = DevRushTheme.colors.blue1)
                            }
                        }

                        is LibraryLoadingState.Error -> {
                            DevRushTopAppBar(
                                title = DevRushTheme.strings.libraryTitle,
                                backButton = false,
                                trailing = {
                                    val notificationCount = viewState.notifications.filter {
                                        it.seenDate == null
                                    }.size
                                    NotificationRow(
                                        modifier = Modifier.align(Alignment.CenterEnd),
                                        countNotifications = notificationCount,
                                        onClicked = { showBottomSheet = true }
                                    )
                                }
                            )
                            PlayingCat(DevRushTheme.strings.globalEmptyStateText)
                        }

                        is LibraryLoadingState.Success -> {
                            DevRushTopAppBar(
                                title = DevRushTheme.strings.libraryTitle,
                                backButton = false,
                                trailing = {
                                    val notificationCount = viewState.notifications.filter {
                                        it.seenDate == null
                                    }.size
                                    NotificationRow(
                                        modifier = Modifier.align(Alignment.CenterEnd),
                                        countNotifications = notificationCount,
                                        onClicked = { showBottomSheet = true }
                                    )
                                }
                            )
                            if (viewState.allLibrary.isEmpty()) {
                                PlayingCat(DevRushTheme.strings.globalEmptyStateText)
                            } else {
                                LibraryView(viewState) { event -> viewModel.obtainEvent(event) }
                            }

                            if (showBottomSheet) {
                                NotificationBottomSheets(
                                    notifications = viewState.notifications,
                                    onDismiss = {
                                        showBottomSheet = false
                                    },
                                    eventHandler = { viewModel.obtainEvent(LibraryEvent.ClickMarkAll) }
                                )
                            }

                            when (viewAction) {
                                null -> {}
                                LibraryAction.PresentNotification -> {
                                    showBottomSheet = true
                                    viewModel.clearAction()
                                }

                                LibraryAction.CloseNotification -> {
                                    showBottomSheet = false
                                    viewModel.clearAction()
                                }

                                is LibraryAction.PresentDetailScreen -> {
                                    val castData = viewAction as LibraryAction.PresentDetailScreen
                                    val detailCourseScreen = rememberScreen(
                                        AppScreensProviders.DetailCourse(
                                            castData.libraryId,
                                            null,
                                            castData.imageCloudKey
                                        )
                                    )
                                    navigator.push(detailCourseScreen)
                                    viewModel.clearAction()
                                }

                                is LibraryAction.PresentSearchScreen -> {
                                    val castData = viewAction as LibraryAction.PresentSearchScreen
                                    val searchScreen = rememberScreen(
                                        AppScreensProviders.Search(
                                            courses = emptyList(), libraries = castData.libraries
                                        )
                                    )
                                    navigator.push(searchScreen)
                                    viewModel.clearAction()
                                }

                                LibraryAction.RetryGetCourse -> {
                                    viewModel.obtainEvent(LibraryEvent.GetCourse(true))
                                    viewModel.clearAction()
                                }
                            }
                        }

                        LibraryLoadingState.Init -> {}
                    }
                }
            },
            isRefreshing = viewState.isRefresh,
            onRefresh = {
                viewModel.obtainEvent(viewEvent = LibraryEvent.RefreshPage(true))
            }
        )
    }
}