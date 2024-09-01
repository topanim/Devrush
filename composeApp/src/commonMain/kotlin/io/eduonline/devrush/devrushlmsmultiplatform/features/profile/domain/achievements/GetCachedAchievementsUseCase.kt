package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.achievements

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository.ProfileRepository

class GetCachedAchievementsUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(): List<Achievement> {
        return profileRepository.getAchievementsFromDatabase()
    }
}