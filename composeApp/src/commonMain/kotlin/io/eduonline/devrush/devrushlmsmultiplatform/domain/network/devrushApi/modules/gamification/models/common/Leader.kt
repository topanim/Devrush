package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Leader(
    val place: Int,
    val score: Int,
    val student: Student,
    val achievements: List<Achievement>,
)