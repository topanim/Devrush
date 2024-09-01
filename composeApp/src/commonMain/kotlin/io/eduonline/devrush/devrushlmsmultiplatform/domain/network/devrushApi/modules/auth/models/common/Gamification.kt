package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Gamification(
    val id: String,
    val enabled: Boolean,
    val achievementStyle: String,
    val shopEnabled: Boolean,

    val canUseLeaderboard: Boolean? = null,
    val leaderboardTime: String? = null,
    val leaderboardEnabled: Boolean,
    val leaderboardTakeCount: Int,
    val leaderboardDuration: Int,
    val leaderboardDurationType: String,

    val pointsName: String,
    val pointsShortName: String,
    val pointsExpireDuration: Int,
    val pointsIconCloudKey: String,
    val pointsExpireDurationType: String,
    val pointsIconType: String,
    val pointsExpirationTime: String? = null,
)
