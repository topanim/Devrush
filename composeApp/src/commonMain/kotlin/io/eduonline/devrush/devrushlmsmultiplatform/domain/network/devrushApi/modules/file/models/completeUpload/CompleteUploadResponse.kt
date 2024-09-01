package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.completeUpload

import kotlinx.serialization.Serializable

@Serializable
data class CompleteUploadResponse(
    val id: String,
    val name: String,
    val mimeType: String,
    val type: String,
    val cloudKey: String,
//    val additionalData: Any?,
    val extension: String,
    val size: Int,
)
