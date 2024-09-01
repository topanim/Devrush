package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun SubscriptionActionButton(
    text: String,
    color: Color,
    textColor: Color = DevRushTheme.colors.baseWhite,
    enabled: Boolean = true,
    onClick: () -> Unit,
) = Box(
    modifier = Modifier
        .defaultMinSize(minWidth = 120.dp)
        .background(
            color = if (enabled) color else DevRushTheme.colors.c5,
            shape = RoundedCornerShape(5.dp)
        )
        .clickable(enabled = enabled, onClick = onClick)
        .padding(horizontal = 12.dp, vertical = 5.dp),
    contentAlignment = Alignment.Center
) {
    Text(
        text,
        style = DevRushTheme.typography.sfPro12,
        color = DevRushTheme.colors.c6
    )
}