package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark

@Composable
fun OutlinedColoredButton(
    text: String,
    color: Color,
    icon: ImageVector,
    addFraming: Boolean = false,
    onClick: () -> Unit,
) {
    val backgroundColor: Color
    val borderColor: Color
    val containerColor = if (LocalThemeIsDark.current) {
        DevRushTheme.colors.c4
    } else DevRushTheme.colors.c6
    if (addFraming) {
        borderColor = DevRushTheme.colors.c5
        backgroundColor = containerColor

    } else {
        backgroundColor = Color.Transparent
        borderColor = Color.Transparent
    }

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        border = BorderStroke(1.dp, color = borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor
        )
    ) {
        Icon(
            icon,
            modifier = Modifier.size(16.dp),
            contentDescription = null,
            tint = color
        )

        Gap(10)

        Text(
            text,
            style = DevRushTheme.typography.sfPro16,
            color = color
        )
    }

}