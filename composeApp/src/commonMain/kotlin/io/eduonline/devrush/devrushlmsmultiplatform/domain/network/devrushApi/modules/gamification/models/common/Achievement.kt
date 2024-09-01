package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Achievement(
    val id: String,
    val title: String? = null,
    val description: String? = null,
    val systemImage: String? = null, // TODO: need to be fixed
    val customCloudKey: String,
    val type: String,
    val subtype: String? = null, // TODO: need to be fixed
    val imageType: String,
    val gamificationStyle: String,
    val systemAchievementId: String? = null,
    val imageCloudKey: String,
    val points: Int,
    val count: Int,
    val achievedDate: String? = null,
)