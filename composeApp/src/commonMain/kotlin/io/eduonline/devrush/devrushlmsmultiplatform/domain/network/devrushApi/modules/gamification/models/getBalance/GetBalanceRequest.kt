package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getBalance

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest


class GetBalanceRequest(
    override val token: String,
    val fields: List<String> = emptyList(),
) : ApiAuthRequest