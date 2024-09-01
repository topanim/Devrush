package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models

sealed interface ProfileEvent {
    data object PresentSettings : ProfileEvent
    data object PresentAchievements : ProfileEvent
    data object PresentLeaderBoard : ProfileEvent
    data object PresentSubscriptions : ProfileEvent
    data class LoadProfile(val expired: Boolean) : ProfileEvent
    data class Refresh(val isRefresh: Boolean) : ProfileEvent
}