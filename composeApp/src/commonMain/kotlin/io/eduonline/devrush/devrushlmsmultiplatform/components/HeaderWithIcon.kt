package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.bell_icon
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun NotificationRow(modifier: Modifier, countNotifications: Int, onClicked: () -> Unit) {
    Row(modifier = modifier) {
        Box {
            Icon(
                modifier = Modifier.clip(RoundedCornerShape(10.dp)).clickable { onClicked() },
                imageVector = vectorResource(Res.drawable.bell_icon),
                contentDescription = null,
                tint = DevRushTheme.colors.c1
            )
            if (countNotifications > 0) {
                Badge(
                    modifier = Modifier.align(Alignment.TopEnd).padding(top = 1.dp, end = 3.dp),
                    containerColor = DevRushTheme.colors.basePink1
                )
            }
        }
        if (countNotifications > 0) {
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = countNotifications.toString(),
                style = DevRushTheme.typography.sfProBold16,
                color = DevRushTheme.colors.blue1
            )
        }
    }
}