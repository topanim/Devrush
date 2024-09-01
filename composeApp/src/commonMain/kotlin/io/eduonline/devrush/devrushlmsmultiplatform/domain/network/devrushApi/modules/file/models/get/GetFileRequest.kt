package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.get

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable


@Serializable
data class GetFileRequest(
    override val token: String,
    val id: String,

    val isPreview: Boolean = true,
) : ApiAuthRequest