package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.completeUpload

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable

@Serializable
data class CompleteUploadRequest(
    override val token: String,

    val eTags: List<ETag>,
    val id: String,
) : ApiAuthRequest

@Serializable
data class ETag(
    val eTag: String,
    val partNumber: Int,
)