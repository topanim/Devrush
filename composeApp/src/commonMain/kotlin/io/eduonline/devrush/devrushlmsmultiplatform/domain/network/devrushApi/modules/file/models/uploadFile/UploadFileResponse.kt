package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.uploadFile

data class UploadFileResponse(
    val eTag: String,
    val partNumber: Int,
)