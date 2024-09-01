package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.profile

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository.ProfileRepository

class GetRemoteProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(): GetMeResponse {
        val remote = profileRepository.getProfileFromServer()
        profileRepository.saveProfile(remote)
        return remote
    }
}