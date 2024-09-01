package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.quiz


import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.authHeader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.quiz.models.get.GetQuizRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.quiz.models.get.GetQuizResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class QuizModule(
    override val client: HttpClient,
    override val domain: String,
    override val path: String,
) : ApiModule {

    private val modulePath =
        "$domain/$path/${io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule.MODULE}"

    companion object {
        const val MODULE = "quiz"
    }

    suspend fun get(
        data: GetQuizRequest,
    ) = client.get("$modulePath/${data.quizId}") {
        authHeader(data)
    }.body<ApiResponse<GetQuizResponse>>()
}