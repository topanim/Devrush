package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun DateTitle(date: String) {
    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(DevRushTheme.colors.c5)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        text = date,
        style = DevRushTheme.typography.sfPro12,
        color = DevRushTheme.colors.c1
    )
}
