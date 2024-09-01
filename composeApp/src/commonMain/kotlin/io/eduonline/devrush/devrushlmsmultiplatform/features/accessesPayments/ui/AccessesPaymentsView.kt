package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsState
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.PaymentFilterTabRow
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.PaymentsTab
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.TitleBlock
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.accesses.AccessesView
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.orders.OrdersView
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.subscriptions.SubscriptionsView
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun AccessesPaymentsView(
    modifier: Modifier = Modifier,
    state: AccessesPaymentsState,
    onEvent: (AccessesPaymentsEvent) -> Unit,
) = Column(
    modifier.verticalScroll(rememberScrollState())
) {
    Column(
        modifier = Modifier.padding(
            start = 20.dp,
            top = 8.dp,
            end = 20.dp,
        )
    ) {
        val tabs = PaymentsTab.toList()
        val selectedTabIndex = remember { mutableStateOf(0) }

        PaymentFilterTabRow(tabs, selectedTabIndex) {
            when (it) {
                PaymentsTab.Active -> onEvent(AccessesPaymentsEvent.GetGlobal(false))
                PaymentsTab.Expired -> onEvent(AccessesPaymentsEvent.GetGlobal(true))
            }
        }

        Gap(24)

        TitleBlock(DevRushTheme.strings.profileSubscriptions) {
            SubscriptionsView(state.subscriptionsFetchState, state.subscriptionsState, onEvent)
        }

        Gap(24)

        TitleBlock(DevRushTheme.strings.profileAccesses) {
            AccessesView(state.accessesFetchState, state.accessesState, onEvent)
        }

        Gap(24)
        if (tabs[selectedTabIndex.value] is PaymentsTab.Active)
            TitleBlock(DevRushTheme.strings.profileOrders) {
                OrdersView(state.ordersFetchState, state.ordersState)
            }
    }
}
