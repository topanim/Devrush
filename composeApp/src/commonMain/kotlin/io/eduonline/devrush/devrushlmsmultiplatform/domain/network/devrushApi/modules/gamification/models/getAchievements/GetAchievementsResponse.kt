package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getAchievements


import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.AchievementsFilter
import kotlinx.serialization.Serializable

@Serializable
data class GetAchievementsResponse(
    val filter: AchievementsFilter,
    val items: List<Achievement>,
)