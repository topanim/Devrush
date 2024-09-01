package io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model

sealed interface LeaderboardEvent {
    data object CloseScreenClicked : LeaderboardEvent
    data class GetLeaderBoard(val retried: Boolean = false) : LeaderboardEvent
    data class Refresh(val refresh: Boolean) : LeaderboardEvent
}