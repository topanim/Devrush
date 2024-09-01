package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation

import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.CourseModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.checkoutSubscriptionRenewal.CheckoutSubscriptionRenewalRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.updateSubscription.SubscriptionState
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.GetAccessesDataUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.GetOrdersDataUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.GetSubscriptionsDataUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.UpdateSubscriptionUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsState
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.FetchState
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.PaymentState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccessesPaymentsViewModel(
    private val studentModule: StudentModule,
    private val courseModule: CourseModule,
    private val tokenRepository: AuthTokenRepository,
    private val getOrdersDataUseCase: GetOrdersDataUseCase,
    private val getSubscriptionsDataUseCase: GetSubscriptionsDataUseCase,
    private val getAccessesDataUseCase: GetAccessesDataUseCase,
    private val updateSubscriptionUseCase: UpdateSubscriptionUseCase,
) : BaseViewModel<AccessesPaymentsState, AccessesPaymentsAction, AccessesPaymentsEvent>(
    AccessesPaymentsState()
) {

    override fun obtainEvent(viewEvent: AccessesPaymentsEvent) = when (viewEvent) {

        is AccessesPaymentsEvent.GetGlobal -> getGlobal(viewEvent.expired)

        is AccessesPaymentsEvent.GetOrders -> getOrders()

        is AccessesPaymentsEvent.GetAccesses -> getAccesses(viewEvent.expired)

        is AccessesPaymentsEvent.GetSubscriptions -> getSubscriptions(viewEvent.expired)

        is AccessesPaymentsEvent.RenewSubscription -> {
            Logger.d("d") { "renew" }
            updateSubscriptionState(viewEvent.id, SubscriptionState.Active)
        }


        is AccessesPaymentsEvent.CancelSubscription -> {
            Logger.d("d") { "cancel" }
            updateSubscriptionState(viewEvent.id, SubscriptionState.Paused)
        }

        is AccessesPaymentsEvent.PaySubscription -> paySubscription(viewEvent.id)

        is AccessesPaymentsEvent.Refresh -> {
            getGlobal(viewState.isActiveTab)
            viewState = viewState.copy(isRefresh = !viewState.isRefresh)
        }

        AccessesPaymentsEvent.ClosePaymentSheet -> {
            viewState = viewState.copy(
                showPaymentSheet = false,
                paymentState = null
            )
        }

        is AccessesPaymentsEvent.OpenCourse -> openCourse(viewEvent)

        is AccessesPaymentsEvent.OpenLibrary -> viewAction = AccessesPaymentsAction.OpenLibrary(
            id = viewEvent.id,
            imageCloudKey = viewEvent.imageCloudKey
        )
    }

    private fun paySubscription(id: String) {
        safeExecute(
            scope = viewModelScope,
            block = {
                val response = studentModule.checkoutSubscriptionRenewal(
                    CheckoutSubscriptionRenewalRequest(id)
                )

                withContext(Dispatchers.Main) {
                    viewState = viewState.copy(
                        showPaymentSheet = true,
                        paymentState = PaymentState(response.headers["Location"]!!)
                    )
                }
            }
        ) {
            Logger.d("d") { "!!: ${it.message}" }
        }
    }

    private fun openCourse(viewEvent: AccessesPaymentsEvent.OpenCourse) {
        safeExecute(
            scope = viewModelScope,
            block = {
                viewAction = AccessesPaymentsAction.OpenCourse(
                    id = viewEvent.id,
                    imageCloudKey = viewEvent.imageCloudKey
                )
            }
        )
    }

    private fun getGlobal(expired: Boolean) {
        getAccesses(expired)
        if (!expired) getOrders()
        getSubscriptions(expired)
        viewState = viewState.copy(isActiveTab = expired)
    }

    private fun updateSubscriptionState(id: String, state: SubscriptionState) {
        safeExecute(
            scope = viewModelScope,
            block = {
                updateSubscriptionUseCase(id, state)
                getSubscriptions(false)
            }
        ) {
            Logger.d("d") { "!: ${it.message}" }
        }
    }

    private fun getAccesses(expired: Boolean) {
        viewState = viewState.copy(accessesFetchState = FetchState.Fetching)

        safeExecute(
            scope = viewModelScope,
            block = {
                val accesses = getAccessesDataUseCase(expired)
                withContext(Dispatchers.Main) {
                    viewState = viewState.copy(
                        accessesFetchState = FetchState.Success,
                        accessesState = accesses
                    )
                }
            }
        ) {
            viewState = viewState.copy(
                accessesFetchState = FetchState.Failure(it.message ?: "")
            )
        }
    }

    private fun getOrders() {
        viewState = viewState.copy(ordersFetchState = FetchState.Fetching)

        safeExecute(
            scope = viewModelScope,
            block = {
                val orders = getOrdersDataUseCase()
                withContext(Dispatchers.Main) {
                    viewState = viewState.copy(
                        ordersFetchState = FetchState.Success,
                        ordersState = orders
                    )
                }
            }
        ) {
            viewState = viewState.copy(
                ordersFetchState = FetchState.Failure(it.message ?: "")
            )
        }
    }

    private fun getSubscriptions(expired: Boolean) {
        viewState = viewState.copy(subscriptionsFetchState = FetchState.Fetching)

        safeExecute(
            scope = viewModelScope,
            block = {
                val subscriptions = getSubscriptionsDataUseCase(expired)
                withContext(Dispatchers.Main) {
                    viewState = viewState.copy(
                        subscriptionsFetchState = FetchState.Success,
                        subscriptionsState = subscriptions
                    )
                }
                refreshing(false)
            }
        ) {
            refreshing(false)
            viewState = viewState.copy(
                subscriptionsFetchState = FetchState.Failure(it.message ?: "")
            )
        }
    }

    private fun refreshing(value: Boolean) {
        viewState = viewState.copy(isRefresh = value)

    }
}
