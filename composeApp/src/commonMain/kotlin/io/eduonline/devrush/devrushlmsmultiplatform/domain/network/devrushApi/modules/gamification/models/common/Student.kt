package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val firstName: String? = null,
    val lastName: String? = null,
    val avatar: Avatar?,
)


@Serializable
data class Avatar(
    val name: String? = null,
    val cloudKey: String? = null,
    val externalUrl: String? = null,
    val size: Int? = null,
    val mimeType: String? = null,
    val type: String? = null,
    val extension: String? = null,
    val createdDate: String? = null,
    val isVoice: Boolean? = null,
    val additionalData: String? = null,
    val id: String? = null,
    val softDeleted: Boolean? = null,
    val softDeletedDate: String? = null,
)
