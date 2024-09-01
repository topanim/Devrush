package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.update

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.domain.SaveNameProfile
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
data class UpdateStudentRequest(
    override val token: String,
    val updateProfileData: UpdateProfileData,

) : ApiAuthRequest

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class UpdateProfileData(
    val email: String? = null,
    val firstName: String,
    val gender: String? = null,
    val avatarId: String? = null,
    val birthday: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val phone: String? = null,
    @EncodeDefault(EncodeDefault.Mode.NEVER) val password: String? = null
)

