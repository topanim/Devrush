package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun CourseBadge(
    text: String,
    icon: ImageVector = Icons.Default.Info,
    iconTint: Color = DevRushTheme.colors.basePink1,
    gap: Int = 10,
    clickable: Boolean = true,
    onClick: () -> Unit = {},
) = Column(
    modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .clickable(enabled = clickable, onClick = onClick)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(2.dp, 8.dp)
    ) {
        Icon(
            icon, contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(20.dp)
        )
        Gap(gap)
        Text(
            text, style = DevRushTheme.typography.sfPro14.copy(
                color = DevRushTheme.colors.c1
            )
        )
    }
}