package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.AUTHORIZATION_PREFIX
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.HEADER_AUTHORIZATION
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header

fun HttpRequestBuilder.authHeader(
    data: ApiAuthRequest,
) {
    header(HEADER_AUTHORIZATION, AUTHORIZATION_PREFIX + data.token)
}