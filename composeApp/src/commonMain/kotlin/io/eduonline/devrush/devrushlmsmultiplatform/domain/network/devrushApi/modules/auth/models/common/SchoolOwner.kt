package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common

import kotlinx.serialization.Serializable

@Serializable
data class SchoolOwner(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String?,
)