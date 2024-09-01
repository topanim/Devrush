package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ColumnScope.AppSettings(
    state: ProfileSettingsState,
    onEvent: (ProfileSettingsEvent) -> Unit = {},
) {
    SettingsTextFieldBlock(
        DevRushTheme.strings.profileNewsUpdatesPromotions,
        enabled = state.fields!!.newsAnnouncementsPromotions,
        onChange = { onEvent(ProfileSettingsEvent.ChangeNewsAnnouncementsPromotions(it)) }
    )

    HorizontalDivider(
        color = DevRushTheme.colors.c3,
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
    )

    val themeOptions = listOf(
        RadioOption(
            DevRushTheme.strings.profileLightTheme,
            Theme.Light
        ),
        RadioOption(
            DevRushTheme.strings.profileDarkTheme,
            Theme.Dark
        ),
        RadioOption(
            DevRushTheme.strings.profileSystemTheme,
            Theme.System
        )
    )

    ChooseThemeField(
        title = DevRushTheme.strings.profileTheme,
        initial = themeOptions.find { it.value == state.fields.theme }!!,
        options = themeOptions,
        onChange = { onEvent(ProfileSettingsEvent.ChangeTheme(it)) }
    )

    Gap(15)
}