package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.search_icon
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SearchRow(onClick: () -> Unit) {

    Row(
        Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, DevRushTheme.colors.c5, RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .background(DevRushTheme.colors.c6)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.search_icon),
            contentDescription = null,
            tint = DevRushTheme.colors.c3
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = DevRushTheme.strings.coursesSearch,
            style = DevRushTheme.typography.sfPro14,
            color = DevRushTheme.colors.c2
        )
    }
}