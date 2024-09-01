package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.check_read
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.SentStatus
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun TimeOfSent(status: SentStatus, time: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        when (status) {
            SentStatus.IS_SENT -> Icon(
                imageVector = Icons.Outlined.Done,
                contentDescription = null,
                tint = DevRushTheme.colors.c2
            )

            SentStatus.IS_DELIVERED -> Icon(
                imageVector = vectorResource(Res.drawable.check_read),
                contentDescription = null,
                tint = DevRushTheme.colors.c2
            )

            SentStatus.IS_READ -> Icon(
                imageVector = vectorResource(Res.drawable.check_read),
                contentDescription = null,
                tint = DevRushTheme.colors.blue2
            )
        }

        Text(
            text = time,
            style = DevRushTheme.typography.sfPro12,
            color = DevRushTheme.colors.c2
        )
    }
}