// Licensed to the .NET Foundation under one or more agreements.
// The .NET Foundation licenses this file to you under the MIT license.
package io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr

import io.ktor.client.HttpClient

/**
 * A builder for configuring [HubConnection] instances.
 */
abstract class HubConnectionBuilder {
    /**
     * Builds a new instance of [HubConnection].
     *
     * @return A new instance of [HubConnection].
     */
    abstract fun build(): HubConnection

    companion object {
        /**
         * Creates a new instance of [HttpHubConnectionBuilder].
         *
         * @param url The URL of the SignalR hub to connect to.
         * @return An instance of [HttpHubConnectionBuilder].
         */
        inline fun create(
            url: String,
            httpClient: HttpClient,
            block: HttpHubConnectionBuilder.() -> Unit = {},
        ): HubConnection {
            if (url.isEmpty()) {
                throw IllegalArgumentException("A valid url is required.")
            }
            return HttpHubConnectionBuilder(url, httpClient).apply(block).build()
        }
    }
}