package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.updateSubscription.SubscriptionState
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.updateSubscription.UpdateSubscriptionRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.updateSubscription.UpdateSubscriptionRequestData


class UpdateSubscriptionUseCase(
    private val api: StudentModule,
    private val tokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(id: String, state: SubscriptionState) {
        api.updateSubscription(
            UpdateSubscriptionRequest(
                tokenRepository.getTokens().accessToken,
                UpdateSubscriptionRequestData(id, state)
            )
        ).checkedRefresh(tokenRepository)
    }
}