package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.common

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ResponseError

interface ChatApiResponse<T> {
    val success: Boolean
    val errors: List<ResponseError>
    val body: T?
    val resetToken: Boolean
}