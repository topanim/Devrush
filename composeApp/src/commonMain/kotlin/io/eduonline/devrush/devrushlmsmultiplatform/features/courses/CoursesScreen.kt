package io.eduonline.devrush.devrushlmsmultiplatform.features.courses

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
import co.touchlab.kermit.Logger
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.notebook_bookmark_icon
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.NotificationRow
import io.eduonline.devrush.devrushlmsmultiplatform.components.PlayingCat
import io.eduonline.devrush.devrushlmsmultiplatform.components.PullToRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.CoursesViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models.CoursesAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models.CoursesEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models.CoursesLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui.CoursesView
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui.views.NotificationBottomSheets
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject

object CoursesScreen : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(vectorResource(Res.drawable.notebook_bookmark_icon))
            val title = DevRushTheme.strings.bottomCourses

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = koinInject<CoursesViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow.parent!!
        var showBottomSheet by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            viewModel.obtainEvent(CoursesEvent.GetCourse())
        }

        PullToRefresh(
            content = {
                Column {
                    when (viewState.fetchCourseInfoRequest) {
                        CoursesLoadingState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = DevRushTheme.colors.blue1)
                            }
                        }

                        is CoursesLoadingState.Error -> {
                            DevRushTopAppBar(
                                title = DevRushTheme.strings.coursesCourses,
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

                        is CoursesLoadingState.Success -> {
                            DevRushTopAppBar(
                                title = DevRushTheme.strings.coursesCourses,
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
                            if (viewState.allCourses.isEmpty()) {
                                PlayingCat(DevRushTheme.strings.globalEmptyStateText)
                            } else {
                                CoursesView(viewState) { event -> viewModel.obtainEvent(event) }
                            }

                            if (showBottomSheet) {
                                NotificationBottomSheets(
                                    notifications = viewState.notifications,
                                    onDismiss = { showBottomSheet = false },
                                    eventHandler = { viewModel.obtainEvent(CoursesEvent.ClickMarkAll) }
                                )
                            }
                        }
                        CoursesLoadingState.Init -> {}
                    }
                }
            },
            isRefreshing = viewState.isRefresh,
            onRefresh = {
                viewModel.obtainEvent(viewEvent = CoursesEvent.Refresh(refresh = true))
            }
        )

        when (viewAction) {
            null -> {}
            CoursesAction.PresentNotifications -> {
                showBottomSheet = true
                viewModel.clearAction()
            }

            CoursesAction.CloseNotifications -> {
                showBottomSheet = false
                viewModel.clearAction()
            }

            is CoursesAction.PresentDetailCourse -> {
                val castData = viewAction as CoursesAction.PresentDetailCourse
                val detailCourseScreen = rememberScreen(
                    AppScreensProviders.DetailCourse(
                        castData.studentCourseId,
                        castData.courseItemId,
                        castData.imageCloudKey
                    )
                )
                navigator.push(detailCourseScreen)
                viewModel.clearAction()
            }

            is CoursesAction.PresentSearchScreen -> {
                val castData = viewAction as CoursesAction.PresentSearchScreen
                val searchScreen = rememberScreen(
                    AppScreensProviders.Search(
                        castData.courses, emptyList()
                    )
                )
                navigator.push(searchScreen)
                viewModel.clearAction()
            }

            CoursesAction.RetryGetCourse -> {
                Logger.d("d") { "action was processed" }
                viewModel.obtainEvent(CoursesEvent.GetCourse(true))
                viewModel.clearAction()
            }
        }
    }
}
