package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun DividerElement() = Row(
    Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    HorizontalDivider(Modifier.fillMaxWidth(0.46f), color = DevRushTheme.colors.c4)
    Text(
        DevRushTheme.strings.authorizationWordBetweenDivider,
        modifier = Modifier.padding(horizontal = 5.dp),
        fontSize = 12.sp,
        color = DevRushTheme.colors.c4
    )
    HorizontalDivider(color = DevRushTheme.colors.c4)
}
