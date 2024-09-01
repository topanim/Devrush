package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi

import io.ktor.client.HttpClient

interface ApiModule {
    val client: HttpClient
    val domain: String
    val path: String
}