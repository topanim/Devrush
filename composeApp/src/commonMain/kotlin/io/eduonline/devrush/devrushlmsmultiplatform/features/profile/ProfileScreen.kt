package io.eduonline.devrush.devrushlmsmultiplatform.features.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import co.touchlab.kermit.Logger
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.settings
import devrush_multiplatform.composeapp.generated.resources.settings_icon
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.PullToRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.ProfileScreenViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.ProfileView
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject

object ProfileScreen : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(
                vectorResource(Res.drawable.settings_icon)
            )

            val title = DevRushTheme.strings.bottomProfile

            return remember {
                TabOptions(
                    index = 3u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = koinInject<ProfileScreenViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow.parent!!

        val settingsScreen = rememberScreen(AppScreensProviders.ProfileSettings)
        val leaderboardScreen = rememberScreen(AppScreensProviders.Leaderboard)
        val achievementsScreen = rememberScreen(AppScreensProviders.Achievement)
        val accessesPaymentsScreen = rememberScreen(AppScreensProviders.AccessesPayments)

        LaunchedEffect(Unit) {
            viewModel.obtainEvent(ProfileEvent.LoadProfile(false))
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DevRushTopAppBar(title = DevRushTheme.strings.bottomProfile,
                backButton = false,
                addDivider = true,
                trailing = {
                    Icon(
                        imageVector = vectorResource(Res.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clip(CircleShape)
                            .clickable { viewModel.obtainEvent(ProfileEvent.PresentSettings) }
                            .padding(8.dp),
                        tint = DevRushTheme.colors.c1
                    )
                }
            )
            PullToRefresh(content = {
                when (viewState.profileLoadingState) {

                    is ProfileLoadingState.Error -> ProfileView(
                        (viewState.profileLoadingState as ProfileLoadingState.Error).message
                    )

                    ProfileLoadingState.Loading -> ProfileView()
                    ProfileLoadingState.Success -> ProfileView(viewState) {
                        viewModel.obtainEvent(
                            it
                        )
                    }

                    ProfileLoadingState.Init -> {}
                }

                when (viewAction) {
                    null -> {}
                    ProfileAction.PresentAchievements -> {
                        navigator.push(achievementsScreen)
                        viewModel.clearAction()
                    }

                    ProfileAction.PresentLeaderBoard -> {
                        navigator.push(leaderboardScreen)
                        viewModel.clearAction()
                    }

                    ProfileAction.PresentSettings -> {
                        navigator.push(settingsScreen)
                        viewModel.clearAction()
                    }

                    ProfileAction.PresentSubscriptions -> {
                        navigator.push(accessesPaymentsScreen)
                        viewModel.clearAction()
                    }
                }
            },
                isRefreshing = viewState.isRefresh,
                onRefresh = {
                    viewModel.obtainEvent(ProfileEvent.Refresh(true))
                    Logger.i(tag = "refreshP", messageString = viewState.isRefresh.toString())
                })
        }
    }
}