package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSubscriptions.GetSubscriptionsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSubscriptions.Subscription
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.updateSubscription.SubscriptionState
import io.eduonline.devrush.devrushlmsmultiplatform.utils.parseStringToLocalDateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class SubscriptionsData(
    val items: List<SubscriptionsDataItem>,
)

data class SubscriptionsDataItem(
    val id: String,
    val imageCloudKey: String?,
    val title: String,
    val amount: Float,
    val beginDate: String,
    val endDate: String,
    val active: Boolean,
    val renewalState: SubscriptionState,
    val expired: Boolean,
)

class GetSubscriptionsDataUseCase(
    private val api: StudentModule,
    private val tokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(
        expired: Boolean,
        useFilter: Boolean = true,
    ): List<SubscriptionsDataItem> {
        return api.getSubscriptions(
            GetSubscriptionsRequest(
                tokenRepository.getTokens().accessToken
            )
        ).checkedRefresh(tokenRepository).checkedResult().items
            .filter {
                if (!useFilter) return@filter true
                val subsEndDate = parseStringToLocalDateTime(it.endDate)
                val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
//                val subscriptionActive = subsEndDate > today
                val subscriptionExpired = it.state != "active"
                        && it.state != "paused"

                subscriptionExpired == expired
            }
            .map {
                it.toSubscriptionDataItem(expired)
            }

    }

    private fun Subscription.toSubscriptionDataItem(expired: Boolean): SubscriptionsDataItem {
        val subsEndDate = parseStringToLocalDateTime(endDate)
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val subscriptionActive = subsEndDate > today
        val subscriptionState = when (state) {
            "active" -> SubscriptionState.Active
            "paused" -> SubscriptionState.Paused
            else -> SubscriptionState.Expired
        }

        return SubscriptionsDataItem(
            id = id,
            title = product.name,
            amount = product.subsPrice,
            beginDate = beginDate,
            endDate = endDate,
            imageCloudKey = product.image.cloudKey,
            active = subscriptionActive,
            renewalState = subscriptionState,
            expired = expired
        )
    }
}