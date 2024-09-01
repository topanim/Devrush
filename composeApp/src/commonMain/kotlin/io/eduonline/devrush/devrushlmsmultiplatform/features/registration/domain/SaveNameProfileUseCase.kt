package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.domain

import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.update.UpdateProfileData
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.update.UpdateStudentRequest

class SaveNameProfileUseCase(
    private val api: StudentModule,
    private val tokenRepository: AuthTokenRepository,
) {

    suspend operator fun invoke(fields: SaveNameProfile) {
        val updateProfileData = UpdateProfileData(
            avatarId = null,
            email = fields.email,
            firstName = fields.name,
            gender = fields.gender,
            lastName = null,
            birthday = null,
            middleName = null,
            phone = null,
            password = null,
        )

        val response = api.update(
            UpdateStudentRequest(
                token = tokenRepository.getTokens().accessToken,
                updateProfileData = updateProfileData
            )
        ).checkedRefresh(tokenRepository)
        Logger.i(tag = "nameResp", messageString = response.toString())
    }
}

data class SaveNameProfile(
    val email:String,
    val name:String,
    val gender: String
)

