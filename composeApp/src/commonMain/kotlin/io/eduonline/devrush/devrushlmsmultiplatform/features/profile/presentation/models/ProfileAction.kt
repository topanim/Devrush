package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models

sealed interface ProfileAction {
    data object PresentSettings : ProfileAction
    data object PresentAchievements : ProfileAction
    data object PresentLeaderBoard : ProfileAction
    data object PresentSubscriptions : ProfileAction
}