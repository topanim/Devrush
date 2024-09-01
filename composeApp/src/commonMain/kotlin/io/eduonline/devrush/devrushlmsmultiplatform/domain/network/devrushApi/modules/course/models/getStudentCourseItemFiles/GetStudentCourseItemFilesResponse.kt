package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItemFiles

import kotlinx.serialization.Serializable


@Serializable
data class GetStudentCourseItemFilesResponse(
    val files: List<StudentCourseItemFile>,
)

@Serializable
data class StudentCourseItemFile(
    val name: String,
    val cloudKey: String,
    val externalUrl: String?,
    val size: Long,
    val mimeType: String,
    val type: String,
    val extension: String,
    val createdDate: String,
    val isVoice: Boolean,
    val additionalData: String?,
    val id: String,
    val softDeleted: Boolean,
    val softDeletedDate: String?,
)