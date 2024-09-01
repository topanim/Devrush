package io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement

sealed interface AchievementsEvent {
    data object OnBackPress : AchievementsEvent
    data class GetCourse(val retried: Boolean = false) : AchievementsEvent
    data class Refresh(val refresh: Boolean = false) : AchievementsEvent
    data class OpenAchievement(val isOpen: Boolean = false) : AchievementsEvent
    data class SetAchievement(val achievement: Achievement? = null) : AchievementsEvent
}