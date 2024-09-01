package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSchoolSettings

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable


@Serializable
data class GetSchoolSettingsRequest(
    override val token: String,
) : ApiAuthRequest