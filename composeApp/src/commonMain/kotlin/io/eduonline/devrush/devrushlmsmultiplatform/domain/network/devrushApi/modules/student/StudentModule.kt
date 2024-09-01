package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.HEADER_REFERER
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.authHeader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.CourseModule.Companion.GET_SCHOOL_SETTINGS_REFERER_URL
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.checkoutSubscriptionRenewal.CheckoutSubscriptionRenewalRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getLicenses.GetLicensesRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getLicenses.GetLicensesResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getOrders.GetOrdersRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getOrders.GetOrdersResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSchoolSettings.GetSchoolSettingsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSchoolSettings.GetSchoolSettingsResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSubscriptions.GetSubscriptionsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSubscriptions.GetSubscriptionsResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.update.UpdateStudentRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.update.UpdateStudentResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.updateSubscription.UpdateSubscriptionRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.updateSubscription.UpdateSubscriptionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class StudentModule(
    override val client: HttpClient,
    override val domain: String,
    override val path: String,
) : ApiModule {

    private val modulePath =
        "$domain/$path/${MODULE}"

    companion object {
        const val MODULE = "student"
        const val UPDATE_STUDENT_REFERER = "https://devrush.eduonline.io/"
        const val SUBSCRIPTION_RENEWAL_URL =
            "https://s7284.accelsite.io/checkout/subscription/renewal/"
    }


    suspend fun update(
        data: UpdateStudentRequest,
    ) = client.put(modulePath) {
        header(HEADER_REFERER, UPDATE_STUDENT_REFERER)
        authHeader(data)

        contentType(ContentType.Application.Json)
        setBody(data.updateProfileData)
    }.body<ApiResponse<UpdateStudentResponse>>()

    suspend fun getLicenses(
        data: GetLicensesRequest,
    ) = client.get("$modulePath/license") {
        authHeader(data)

        parameter("skip", data.skip)
        parameter("take", data.take)
        parameter("softDeleted", data.softDeleted)
        parameter("useSort", data.useSort)
        parameter("useBaseFilter", data.useBaseFilter)
        parameter("useItemsTotal", data.useItemsTotal)
        parameter("fields", data.fields)
    }.body<ApiResponse<GetLicensesResponse>>()

    suspend fun getSubscriptions(
        data: GetSubscriptionsRequest,
    ) = client.get("$modulePath/subscription") {
        authHeader(data)

        parameter("skip", data.skip)
        parameter("take", data.take)
        parameter("softDeleted", data.softDeleted)
        parameter("useSort", data.useSort)
        parameter("useBaseFilter", data.useBaseFilter)
        parameter("useItemsTotal", data.useItemsTotal)
        parameter("fields", data.fields)
    }.body<ApiResponse<GetSubscriptionsResponse>>()

    suspend fun updateSubscription(
        data: UpdateSubscriptionRequest,
    ) = client.put("$modulePath/subscription") {
        authHeader(data)

        contentType(ContentType.Application.Json)
        setBody(data.data)
    }.body<ApiResponse<UpdateSubscriptionResponse>>()

    suspend fun checkoutSubscriptionRenewal(
        data: CheckoutSubscriptionRenewalRequest,
    ) = client.request(SUBSCRIPTION_RENEWAL_URL + data.id)


    suspend fun getOrders(
        data: GetOrdersRequest,
    ) = client.get("$modulePath/order") {
        authHeader(data)

        parameter("skip", data.skip)
        parameter("take", data.take)
        parameter("softDeleted", data.softDeleted)
        parameter("useSort", data.useSort)
        parameter("useBaseFilter", data.useBaseFilter)
        parameter("useItemsTotal", data.useItemsTotal)
        parameter("fields", data.fields)
    }.body<ApiResponse<GetOrdersResponse>>()

    suspend fun getSchoolSettings(
        data: GetSchoolSettingsRequest,
    ) = client.get("$domain/$path/school-setting") {
        header(HEADER_REFERER, GET_SCHOOL_SETTINGS_REFERER_URL)
        authHeader(data)
    }.body<ApiResponse<GetSchoolSettingsResponse>>()
}