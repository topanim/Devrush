package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.beginUpload

import kotlinx.serialization.Serializable

@Serializable
data class BeginUploadResponse(
    val id: String,
    val urls: List<String>,
)