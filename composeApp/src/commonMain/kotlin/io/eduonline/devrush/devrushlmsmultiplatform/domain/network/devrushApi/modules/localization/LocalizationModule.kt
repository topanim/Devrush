package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.localization


import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiModule
import io.ktor.client.HttpClient

class LocalizationModule(
    override val client: HttpClient,
    override val domain: String,
    override val path: String,
) : ApiModule {
    private val modulePath =
        "$domain/$path/${io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.localization.LocalizationModule.Companion.MODULE}"

    companion object {
        const val MODULE = "authorization"
//        const val LOGIN_REFERER_URL = "https://devrush.eduonline.io/login?returnUrl=/"
    }
}