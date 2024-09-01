package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation

import androidx.lifecycle.viewModelScope
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.ExpiredTokenException
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Leader
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.GetSubscriptionsDataUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.SubscriptionsDataItem
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.achievements.GetCachedAchievementsUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.achievements.GetRemoteAchievementsUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.leader.GetCachedLeaderboardUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.leader.GetRemoteLeaderboardUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.profile.GetCachedProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.profile.GetRemoteProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileFields
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileScreenViewModel : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>(
    ProfileState()
), KoinComponent {

    private val getRemoteProfileUseCase: GetRemoteProfileUseCase by inject()
    private val getCachedProfileUseCase: GetCachedProfileUseCase by inject()
    private val getCachedAchievementsUseCase: GetCachedAchievementsUseCase by inject()
    private val getRemoteAchievementsUseCase: GetRemoteAchievementsUseCase by inject()
    private val getCachedLeaderboardUseCase: GetCachedLeaderboardUseCase by inject()
    private val getRemoteLeaderBoardUseCase: GetRemoteLeaderboardUseCase by inject()
    private val getSubscriptionsDataUseCase: GetSubscriptionsDataUseCase by inject()

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when (viewEvent) {
            is ProfileEvent.LoadProfile -> loadProfile(viewEvent.expired, false)
            is ProfileEvent.Refresh -> {
                loadProfile(true, false)
                viewState = viewState.copy(isRefresh = !viewState.isRefresh)
                viewState = viewState.copy(profileLoadingState = ProfileLoadingState.Loading)
            }

            ProfileEvent.PresentAchievements -> viewAction = ProfileAction.PresentAchievements
            ProfileEvent.PresentLeaderBoard -> viewAction = ProfileAction.PresentLeaderBoard
            ProfileEvent.PresentSettings -> viewAction = ProfileAction.PresentSettings
            ProfileEvent.PresentSubscriptions -> viewAction = ProfileAction.PresentSubscriptions
        }
    }

    private fun refresh(refresh: Boolean) {
        viewState = viewState.copy(isRefresh = refresh)
    }

    private fun loadProfile(expired: Boolean, retried: Boolean) {
        safeExecute(
            scope = viewModelScope,
            block = {
                val cachedProfile = getCachedProfileUseCase()
                val cachedAchievements = getCachedAchievementsUseCase()
                val cachedLeaderboard = getCachedLeaderboardUseCase()
                if (cachedProfile != null && cachedAchievements.isNotEmpty() && cachedLeaderboard.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        updateUI(
                            profile = cachedProfile,
                            leaders = cachedLeaderboard,
                            subscription = emptyList(),
                            achievements = cachedAchievements
                        )
                    }
                }

                if (viewState.isRefresh) ProfileLoadingState.Loading
                val leaders = getRemoteLeaderBoardUseCase()
                val subscriptions = getSubscriptionsDataUseCase(false, false)
                val achievements = getRemoteAchievementsUseCase()
                val remoteProfile = getRemoteProfileUseCase()
                withContext(Dispatchers.Main) {
                    updateUI(remoteProfile, leaders, subscriptions, achievements)
                }
                refresh(false)
            }
        ) {
                viewState = viewState.copy(
                profileLoadingState = ProfileLoadingState.Error(it.message ?: "")
            )
        }
    }

    private fun updateUI(
        profile: GetMeResponse,
        leaders: List<Leader>,
        subscription: List<SubscriptionsDataItem>,
        achievements: List<Achievement>,
    ) {
        viewState = viewState.copy(
            profile = profile,
            fields = ProfileFields(
                profile,
                darkTheme = false
            ),
            profileLoadingState = ProfileLoadingState.Success,
            leaderList = leaders,
            subscriptionsState = subscription,
            achievementsActivity = achievements.filter { it.count != 0 },
            achievementsNoActivity = achievements.filter { it.count == 0 }
        )
    }
}