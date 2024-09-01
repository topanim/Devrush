package io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models

sealed interface AchievementsAction {
    data object PresencePast : AchievementsAction
    data object RetryGetCourse : AchievementsAction
}