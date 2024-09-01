package io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Leader

sealed class LeaderboardLoadingState {
    data object Loading : LeaderboardLoadingState()
    data object Success : LeaderboardLoadingState()
    data class Error(val message: String) : LeaderboardLoadingState()

}

data class LeaderboardViewState(
    val fetchLeaderBoardRequest: LeaderboardLoadingState = LeaderboardLoadingState.Loading,
    val isRefresh: Boolean = false,
    val leaderList: List<Leader> = emptyList(),
    val currentUser: Leader? = null
)

