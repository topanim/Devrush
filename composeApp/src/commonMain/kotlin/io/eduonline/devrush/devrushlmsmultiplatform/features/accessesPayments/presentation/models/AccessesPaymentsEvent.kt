package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models

sealed interface AccessesPaymentsEvent {
    data class GetGlobal(val expired: Boolean) : AccessesPaymentsEvent
    data class GetAccesses(val expired: Boolean) : AccessesPaymentsEvent
    data object GetOrders : AccessesPaymentsEvent
    data class GetSubscriptions(val expired: Boolean) : AccessesPaymentsEvent

    data object ClosePaymentSheet : AccessesPaymentsEvent

    data class OpenCourse(val id: String, val imageCloudKey: String?) : AccessesPaymentsEvent
    data class OpenLibrary(val id: String, val imageCloudKey: String?) : AccessesPaymentsEvent
    data class CancelSubscription(val id: String) : AccessesPaymentsEvent
    data class RenewSubscription(val id: String) : AccessesPaymentsEvent
    data class Refresh(val refresh: Boolean) : AccessesPaymentsEvent
    data class PaySubscription(val id: String) : AccessesPaymentsEvent
}