package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.AccessDataItem
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.OrderDataItem
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.SubscriptionsDataItem


sealed interface FetchState {
    data object Fetching : FetchState
    data object Success : FetchState
    data class Failure(val error: String) : FetchState
}


data class AccessesPaymentsState(
    val globalFetchState: FetchState = FetchState.Fetching,

    val accessesFetchState: FetchState = FetchState.Fetching,
    val accessesState: List<AccessDataItem> = emptyList(),

    val ordersFetchState: FetchState = FetchState.Fetching,
    val ordersState: List<OrderDataItem> = emptyList(),

    val subscriptionsFetchState: FetchState = FetchState.Fetching,
    val subscriptionsState: List<SubscriptionsDataItem> = emptyList(),
    val isRefresh: Boolean = false,

    val showPaymentSheet: Boolean = false,
    val paymentState: PaymentState? = null,

    val isActiveTab: Boolean = false,
)

data class PaymentState(
    val paymentUrl: String,
)