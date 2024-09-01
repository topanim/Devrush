package io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.PlayingCat
import io.eduonline.devrush.devrushlmsmultiplatform.components.PullToRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.LeaderboardViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model.LeaderboardAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model.LeaderboardEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model.LeaderboardLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui.LeaderBoardView
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.koin.compose.koinInject

class LeaderboardScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: LeaderboardViewModel = koinInject<LeaderboardViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow


        PullToRefresh(content = {

            Column(
                modifier = Modifier.padding(
                    WindowInsets.systemBars.only(WindowInsetsSides.Vertical).asPaddingValues()
                )
            ) {

                when (viewState.fetchLeaderBoardRequest) {
                    LeaderboardLoadingState.Loading -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = DevRushTheme.colors.blue1)
                    }

                    is LeaderboardLoadingState.Error -> {
                        DevRushTopAppBar(title = DevRushTheme.strings.profileLeaderboard) { }
                        HorizontalDivider(thickness = 1.dp, color = DevRushTheme.colors.c5)
                        PlayingCat(DevRushTheme.strings.globalEmptyStateText)
                    }

                    is LeaderboardLoadingState.Success ->
                        LeaderBoardView(viewState) { viewModel.obtainEvent(it) }
                }
            }

            LaunchedEffect(Unit) {
                viewModel.obtainEvent(LeaderboardEvent.GetLeaderBoard())
            }

            when (viewAction) {
                null -> {}
                LeaderboardAction.CloseScreen -> {
                    navigator.pop()
                    viewModel.clearAction()
                }

                LeaderboardAction.GetLeaderBoard -> {
                    viewModel.obtainEvent(
                        LeaderboardEvent.GetLeaderBoard(retried = true)
                    )
                    viewModel.clearAction()
                }
            }
        },
            isRefreshing = viewState.isRefresh,
            onRefresh = {
                viewModel.obtainEvent(LeaderboardEvent.Refresh(true))
            })
    }
}