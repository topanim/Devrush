package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun SettingsBlock(
    title: String,
    content: @Composable () -> Unit,
) = Column(Modifier.fillMaxWidth()) {
    Text(
        title,
        style = DevRushTheme.typography.sfProBold16,
        color = DevRushTheme.colors.c1
    )

    Gap(16)

    content()
}