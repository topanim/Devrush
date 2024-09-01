package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Avatar
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.ChatUserData
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Group
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.School
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Session
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Tag
import kotlinx.serialization.Serializable


@Serializable
data class GetMeResponse(
    val id: String,
    val email: String,
    val firstName: String,
    val middleName: String?,
    val lastName: String?,
    val phone: String?,
    val lang: String,
    val gender: String,
    val birthday: String?,
    val bonusBalance: Float,
    val isEmailConfirmed: Boolean,
    val isSchoolEmailConfirmed: Boolean,
    val emailConfirmationSentDate: String?,
    val isPhoneConfirmed: Boolean,
    val phoneConfirmationSentDate: String?,
    val needChangePassword: Boolean,
    val isAdmin: Boolean,
    val concurrencyStamp: String,
    val roles: List<String>, // TODO: need to be fixed
    val school: School,
    val avatar: Avatar?,
    val session: Session,
    val chatUserData: ChatUserData,
    val tags: List<Tag>,
    val groups: List<Group>,
)


















