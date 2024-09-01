package io.eduonline.devrush.devrushlmsmultiplatform.components.filterRow.filterCell

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ItemFilterCell(
    filterCellModel: FilterCellModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    AssistChip(
        modifier = modifier,
        onClick = onClick,
        label = {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = if (filterCellModel.filter == "Все") DevRushTheme.strings.all
                else filterCellModel.filter,
                style = DevRushTheme.typography.sfPro14,
                color =
                if (filterCellModel.isSelected)
                    DevRushTheme.colors.baseWhite
                else
                    DevRushTheme.colors.c2
            )
        },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            1.dp,
            if (filterCellModel.isSelected)
                DevRushTheme.colors.blue2
            else
                DevRushTheme.colors.c2
        ),
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (filterCellModel.isSelected)
                DevRushTheme.colors.blue2
            else
                DevRushTheme.colors.c6
        )
    )
}