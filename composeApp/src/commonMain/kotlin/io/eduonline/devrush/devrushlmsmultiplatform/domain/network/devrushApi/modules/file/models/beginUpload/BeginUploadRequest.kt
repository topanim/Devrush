package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.beginUpload

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.common.FileType

data class BeginUploadRequest(
    override val token: String,

    val contentSize: Int,
    val fileName: String,
    val fileType: FileType,
    val fileExtension: String,
    val chunkSize: Int = 52428800,
    val isPublic: Boolean = true,
    val fileFullName: String = "$fileName$fileExtension",
    val contentType: String = "$fileType/$fileExtension",
) : ApiAuthRequest