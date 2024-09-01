package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.achievements

import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository.ProfileRepository

class GetRemoteAchievementsUseCase(
    private val profileRepository: ProfileRepository,
) {

    suspend operator fun invoke(): List<Achievement> {
        val remote = profileRepository.getAchievementsFromServer()
        profileRepository.saveAchievements(remote)
        Logger.d("d") { "GetRemoteAchievementsUseCase: $remote" }
        return remote
    }
}