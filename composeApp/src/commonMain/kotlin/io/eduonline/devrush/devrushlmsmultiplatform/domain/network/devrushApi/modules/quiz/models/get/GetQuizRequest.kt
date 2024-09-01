package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.quiz.models.get

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable


@Serializable
data class GetQuizRequest(
    override val token: String,

    val quizId: String,
) : ApiAuthRequest