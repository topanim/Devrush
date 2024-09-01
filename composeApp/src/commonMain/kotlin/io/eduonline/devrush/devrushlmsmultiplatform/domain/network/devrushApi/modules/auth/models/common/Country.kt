package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val id: String,
    val softDeleted: Boolean,
    val softDeletedDate: String?,
)