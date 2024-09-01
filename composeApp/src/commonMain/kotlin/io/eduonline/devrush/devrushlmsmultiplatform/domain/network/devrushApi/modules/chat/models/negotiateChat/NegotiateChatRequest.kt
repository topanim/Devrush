package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.negotiateChat

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest

data class NegotiateChatRequest(
    override val token: String,
    val negotiateVersion: Int = 1,
) : ApiAuthRequest