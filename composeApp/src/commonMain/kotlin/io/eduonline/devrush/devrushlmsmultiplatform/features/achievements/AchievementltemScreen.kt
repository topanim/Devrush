package io.eduonline.devrush.devrushlmsmultiplatform.features.achievements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.eduonline.devrush.devrushlmsmultiplatform.components.PullToRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.AchievementsViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models.AchieveLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models.AchievementsAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models.AchievementsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.ui.AchievementError
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.ui.AchievementLoading
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.ui.AchievementsView
import org.koin.compose.koinInject

class AchievementsScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: AchievementsViewModel = koinInject<AchievementsViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) { viewModel.obtainEvent(AchievementsEvent.GetCourse()) }

        PullToRefresh(content = {
            when (viewState.fetchCourseInfoRequest) {
                is AchieveLoadingState.Error -> AchievementError(
                    (viewState.fetchCourseInfoRequest as AchieveLoadingState.Error).message
                )

                AchieveLoadingState.Loading -> AchievementLoading()
                AchieveLoadingState.Success -> AchievementsView(viewState) { event ->
                    viewModel.obtainEvent(event)
                }
            }

            when (viewAction) {
                null -> {}
                AchievementsAction.PresencePast -> {
                    navigator.pop()
                    viewModel.clearAction()
                }

                AchievementsAction.RetryGetCourse -> {
                    viewModel.clearAction()
                    viewModel.obtainEvent(AchievementsEvent.GetCourse(true))
                }
            }
        }, isRefreshing = viewState.isRefresh,
            onRefresh = {
                viewModel.obtainEvent(viewEvent = AchievementsEvent.Refresh(true))
            }
        )
    }
}