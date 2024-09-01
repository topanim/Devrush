package io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation

import androidx.lifecycle.viewModelScope
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.ExpiredTokenException
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Leader
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model.LeaderboardAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model.LeaderboardEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model.LeaderboardLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model.LeaderboardViewState
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.leader.GetCachedLeaderboardUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.profile.GetCachedProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class LeaderboardViewModel(
    private val getCachedLeaderboardUseCase: GetCachedLeaderboardUseCase,
    private val getCachedProfileUseCase: GetCachedProfileUseCase,
) : BaseViewModel<LeaderboardViewState, LeaderboardAction, LeaderboardEvent>(
    LeaderboardViewState()
), KoinComponent {

    override fun obtainEvent(viewEvent: LeaderboardEvent) {
        when (viewEvent) {
            LeaderboardEvent.CloseScreenClicked -> viewAction = LeaderboardAction.CloseScreen
            is LeaderboardEvent.GetLeaderBoard -> getLeaderBoard(viewEvent.retried)
            is LeaderboardEvent.Refresh -> {
                getLeaderBoard(true)
                viewState = viewState.copy(isRefresh = !viewState.isRefresh)
            }
        }
    }

    private fun getLeaderBoard(retried: Boolean) {
        safeExecute(
            scope = viewModelScope,
            block = {
                val leaderboardData = getCachedLeaderboardUseCase()
                val profile = getCachedProfileUseCase()
                withContext(Dispatchers.Main) {
                    updateUI(leaderboardData, profile)
                    updateFetchState(LeaderboardLoadingState.Success)
                }
                if (viewState.isRefresh) {
                    updateFetchState(LeaderboardLoadingState.Loading)
                    val refreshData = getCachedLeaderboardUseCase()
                    withContext(Dispatchers.Main) {
                        updateUI(refreshData, profile)
                        updateFetchState(LeaderboardLoadingState.Success)
                    }
                }
                refreshing(false)
            }
        ) {
            refreshing(false)
            if (it is ExpiredTokenException && !retried) {
                getLeaderBoard(true)
            } else updateFetchState(
                LeaderboardLoadingState.Error(it.message ?: "")
            )
        }
    }

    private fun updateUI(leaderList: List<Leader>, profile: GetMeResponse?) {
        viewState = viewState.copy(
            leaderList = leaderList,
            currentUser = leaderList.find {
                it.student.avatar?.cloudKey == profile?.avatar?.cloudKey &&
                        it.student.firstName == profile?.firstName &&
                        it.student.lastName == profile?.lastName
            }
        )
    }

    private fun refreshing(value: Boolean) {
        viewState = viewState.copy(isRefresh = value)

    }

    private fun updateFetchState(state: LeaderboardLoadingState) {
        viewState = viewState.copy(fetchLeaderBoardRequest = state)
    }
}