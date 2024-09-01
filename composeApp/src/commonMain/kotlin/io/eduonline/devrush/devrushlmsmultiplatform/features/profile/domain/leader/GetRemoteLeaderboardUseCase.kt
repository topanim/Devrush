package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.leader

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Leader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository.ProfileRepository

class GetRemoteLeaderboardUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(): List<Leader> {
        val remote = profileRepository.getLeaderBoardFromServer()
        profileRepository.saveLeaderBoard(remote)
        return remote
    }
}
