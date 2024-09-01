package io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr.transports

import io.ktor.client.HttpClient
import okio.BufferedSource
import okio.Closeable

interface Response : Closeable {
    val isSuccessful: Boolean
    val body: Body?

    interface Body {
        val contentType: String?
        fun source(): BufferedSource
    }
}

internal expect class ServerSentEventsDelegate constructor(client: HttpClient) {
    fun get(
        url: String,
        headers: Map<String, String>,
        onFailure: (Exception) -> Unit,
        onSuccess: (Response) -> Unit,
    )

    fun post(url: String, headers: Map<String, String>, message: ByteArray)
}
