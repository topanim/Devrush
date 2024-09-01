package io.eduonline.devrush.devrushlmsmultiplatform.libs.transports

import io.ktor.client.HttpClient

internal actual class ServerSentEventsDelegate actual constructor(client: HttpClient) {
    actual fun get(
        url: String,
        headers: Map<String, String>,
        onFailure: (Exception) -> Unit,
        onSuccess: (Response) -> Unit
    ) {
        throw NotImplementedError()
    }

    actual fun post(
        url: String,
        headers: Map<String, String>,
        message: ByteArray
    ) {
        throw NotImplementedError()
    }

}