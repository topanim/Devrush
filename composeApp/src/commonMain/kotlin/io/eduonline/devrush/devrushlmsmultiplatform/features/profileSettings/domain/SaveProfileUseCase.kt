package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.database.AppDatabase
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.update.UpdateProfileData
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.update.UpdateStudentRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository.ProfileRepository
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.profile.GetRemoteProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsFields

class SaveProfileUseCase(
    private val api: StudentModule,
    private val tokenRepository: AuthTokenRepository,
    private val getLoginUseCase: GetLoginUseCase,
    private val getRemoteProfileUseCase: GetRemoteProfileUseCase
) {
    suspend operator fun invoke(fields: ProfileSettingsFields) {
        val updateProfileData = UpdateProfileData(
            avatarId = fields.imageId,
            email = fields.email,
            firstName = fields.name,
            gender = fields.gender,
            lastName = fields.surname,
            birthday = fields.birthdate,
            middleName = fields.patronymic,
            phone = fields.phone,
            password = fields.password.ifEmpty { null }
        )

        val response = api.update(
            UpdateStudentRequest(
                token = tokenRepository.getTokens().accessToken,
                updateProfileData = updateProfileData
            )
        ).checkedRefresh(tokenRepository)
        if (fields.password.isNotEmpty()) {
            getLoginUseCase(fields.password, fields.email)
        }
        getRemoteProfileUseCase.invoke()

    }
}