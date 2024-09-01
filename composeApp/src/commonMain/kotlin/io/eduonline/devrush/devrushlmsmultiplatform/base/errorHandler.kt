package io.eduonline.devrush.devrushlmsmultiplatform.base

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiResponse

fun ApiResponse<*>.checkResult() {
    if (!this.success) throw Exception(this.errors.first().message)
}

fun <T : Any> ApiResponse<T>.checkedResult(): T {
    this.checkResult()
    return this.body!!
}