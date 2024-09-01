package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun Title(modifier: Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = DevRushTheme.typography.sfProBold18,
        color = DevRushTheme.colors.c1
    )
}