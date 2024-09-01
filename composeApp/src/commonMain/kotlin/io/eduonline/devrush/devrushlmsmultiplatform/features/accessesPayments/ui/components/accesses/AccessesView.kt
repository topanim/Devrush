package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.accesses

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.AccessDataItem
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.AccessesDataItemType
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.FetchState
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.LoadingShimmer
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ColumnScope.AccessesView(
    fetchState: FetchState,
    state: List<AccessDataItem>,
    onEvent: (AccessesPaymentsEvent) -> Unit,
) {
    when (fetchState) {
        FetchState.Fetching -> LoadingShimmer()
        is FetchState.Failure -> {
            Text(fetchState.error)
        }

        FetchState.Success -> AccessesListView(state, onEvent)
    }
}

@Composable
fun ColumnScope.AccessesListView(
    state: List<AccessDataItem>,
    onEvent: (AccessesPaymentsEvent) -> Unit,
) = if (state.isEmpty()) Text(
    text = DevRushTheme.strings.accessesPaymentsEmptyPlug,
    style = DevRushTheme.typography.sfPro14,
    color = DevRushTheme.colors.c2,
    modifier = Modifier.padding(vertical = 10.dp)
) else state.forEach {
    Gap(10)

    AccessView(
        id = it.id,
        title = it.name,
        banner = it.imageCloudKey,
        type = it.type,
        startDate = it.startDate,
        endDate = it.endDate,
        rate = it.rate
    ) {
        when (it.type) {
            AccessesDataItemType.Course ->
                onEvent(AccessesPaymentsEvent.OpenCourse(it.id, it.imageCloudKey))
            AccessesDataItemType.Library ->
                onEvent(AccessesPaymentsEvent.OpenLibrary(it.id, it.imageCloudKey))
        }
    }
}

