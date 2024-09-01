package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification


import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.HEADER_REFERER
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.authHeader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getAchievements.GetAchievementsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getAchievements.GetAchievementsResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getBalance.GetBalanceRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getLeaders.GetLeadersRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getLeaders.GetLeadersResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getProducts.GetProductsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getProducts.GetProductsResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.totals.GetTotalsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.totals.GetTotalsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter

class GamificationModule(
    override val client: HttpClient,
    override val domain: String,
    override val path: String,
) : ApiModule {
    private val modulePath = "$domain/$path/${MODULE}"

    companion object {
        const val MODULE = "gamification"
        const val GET_BALANCE_REFERER = "https://devrush.eduonline.io/"
    }

    suspend fun getBalance(
        data: GetBalanceRequest,
    ) = client.get("$modulePath/balance") {
        header(HEADER_REFERER, GET_BALANCE_REFERER)
        authHeader(data)

        parameter("fields", data.fields)
    }.body<ApiResponse<Int>>()

    suspend fun getTotals(
        data: GetTotalsRequest,
    ) = client.get("$modulePath/totals") {
        authHeader(data)
    }.body<ApiResponse<GetTotalsResponse>>()

    suspend fun getAchievements(
        data: GetAchievementsRequest,
    ) = client.get("$modulePath/achievement") {
        authHeader(data)

        parameter("skip", data.skip)
        parameter("take", data.take)
        parameter("softDeleted", data.softDeleted)
        parameter("useSort", data.useSort)
        parameter("useBaseFilter", data.useBaseFilter)
        parameter("useItemsTotal", data.useItemsTotal)
        parameter("showAll", data.showAll)
    }.body<ApiResponse<GetAchievementsResponse>>()

    suspend fun getLeaders(
        data: GetLeadersRequest,
    ) = client.get("$modulePath/leaders") {
        authHeader(data)

        parameter("skip", data.skip)
        parameter("take", data.take)
        parameter("softDeleted", data.softDeleted)
        parameter("useSort", data.useSort)
        parameter("useBaseFilter", data.useBaseFilter)
        parameter("useItemsTotal", data.useItemsTotal)
    }.body<ApiResponse<GetLeadersResponse>>()

    suspend fun getProducts(
        data: GetProductsRequest,
    ) = client.get("$modulePath/leaders") {
        authHeader(data)
    }.body<ApiResponse<GetProductsResponse>>()
}