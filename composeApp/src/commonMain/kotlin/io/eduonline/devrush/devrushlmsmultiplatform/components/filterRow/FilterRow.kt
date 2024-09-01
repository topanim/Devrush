package io.eduonline.devrush.devrushlmsmultiplatform.components.filterRow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.filterRow.filterCell.FilterCellModel
import io.eduonline.devrush.devrushlmsmultiplatform.components.filterRow.filterCell.ItemFilterCell

@Composable
fun FilterRow(filters: List<FilterCellModel>, onClick: (String) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(filters) { filter ->
            ItemFilterCell(
                filter,
                modifier = Modifier
                    .padding(start = if (filter == filters.first()) 20.dp else 0.dp)
                    .padding(end = if (filter == filters.last()) 20.dp else 0.dp),
            ) {
                onClick(filter.filter)
            }
        }
    }
}