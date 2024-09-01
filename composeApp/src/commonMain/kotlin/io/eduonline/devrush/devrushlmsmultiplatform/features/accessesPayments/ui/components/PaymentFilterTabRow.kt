package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

sealed interface PaymentsTab {
    data object Active : PaymentsTab
    data object Expired : PaymentsTab

    companion object {
        fun toList() = listOf(Active, Expired)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentFilterTabRow(
    tabs: List<PaymentsTab>,
    selectedTabIndex: MutableState<Int>,
    onTabChanged: (PaymentsTab) -> Unit,
) {
    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex.value,
        containerColor = Color.Transparent,
        contentColor = DevRushTheme.colors.blue1,
        divider = {},
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                color = DevRushTheme.colors.blue1,
                modifier = Modifier.tabIndicatorOffset(
                    selectedTabIndex.value,
                    matchContentSize = true
                ),
                width = Dp.Unspecified,
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex.value == index,
                onClick = {
                    selectedTabIndex.value = index
                    onTabChanged(tab)
                },
                text = {
                    Text(
                        when (tab) {
                            PaymentsTab.Active -> DevRushTheme.strings.profileActive
                            PaymentsTab.Expired -> DevRushTheme.strings.profileExpired
                        }
                    )
                },
                selectedContentColor = DevRushTheme.colors.blue1,
                modifier = Modifier.clip(RoundedCornerShape(15.dp))
            )
        }
    }
}