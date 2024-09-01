package io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model

sealed interface LeaderboardAction {
    data object CloseScreen : LeaderboardAction
    data object GetLeaderBoard : LeaderboardAction
}