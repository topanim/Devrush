package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.orders

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.OrderDataItem
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.FetchState
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.LoadingShimmer
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ColumnScope.OrdersView(fetchState: FetchState, state: List<OrderDataItem>) {
    when (fetchState) {
        FetchState.Fetching -> LoadingShimmer()
        is FetchState.Failure -> {
            Text(fetchState.error)
        }

        FetchState.Success -> OrdersListView(fetchState, state)
    }
}

@Composable
fun ColumnScope.OrdersListView(fetchState: FetchState, state: List<OrderDataItem>) {
    if (state.isEmpty()) Text(
        text = DevRushTheme.strings.accessesPaymentsEmptyPlug,
        style = DevRushTheme.typography.sfPro14,
        color = DevRushTheme.colors.c2,
        modifier = Modifier.padding(vertical = 10.dp)
    ) else state.forEach {
        Gap(10)

        OrderView(
            number = it.number,
            amount = it.paidAmount,
            creationDate = it.createdDate
        ) {}
    }
}