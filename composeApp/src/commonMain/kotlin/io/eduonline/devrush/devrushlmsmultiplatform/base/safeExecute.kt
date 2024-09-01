package io.eduonline.devrush.devrushlmsmultiplatform.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun safeExecute(
    context: CoroutineContext = Dispatchers.IO,
    scope: CoroutineScope = CoroutineScope(context),
    block: suspend CoroutineScope.(retried: Boolean) -> Unit,
    onFailure: suspend CoroutineScope.(e: Exception) -> Unit = {},
) = scope.launch(context) {
    try {
        block(false)
    } catch (e: ExpiredTokenException) {
        try {
            block(true)
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}