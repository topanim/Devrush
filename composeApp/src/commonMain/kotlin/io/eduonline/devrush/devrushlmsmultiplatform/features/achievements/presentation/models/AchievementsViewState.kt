package io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement

data class AchievementsViewState(
    val fetchCourseInfoRequest: AchieveLoadingState = AchieveLoadingState.Loading,
    val achievementsActivity: List<Achievement>? = null,
    val achievementsNoActivity: List<Achievement>? = null,
    val isRefresh: Boolean = false,
    val openAchievement: Boolean = false,
    val currentAchievement: Achievement? = null,
)

sealed interface AchieveLoadingState {
    data object Loading : AchieveLoadingState
    data object Success : AchieveLoadingState
    data class Error(val message: String) : AchieveLoadingState
}