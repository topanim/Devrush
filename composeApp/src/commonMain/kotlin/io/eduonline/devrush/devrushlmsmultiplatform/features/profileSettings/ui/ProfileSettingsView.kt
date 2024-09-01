package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.ic_logout
import devrush_multiplatform.composeapp.generated.resources.ic_trash_bin
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsState
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components.AccountSettings
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components.AppSettings
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components.OutlinedColoredButton
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components.PasswordSettings
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components.ProfileSettingsImage
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components.SettingsBlock
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun ProfileSettingsView(
    state: ProfileSettingsState,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    onEvent: (ProfileSettingsEvent) -> Unit = {},
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            }
    ) {
        Gap(20)

        ProfileSettingsImage(state, onEvent)

        Gap(20)

        SettingsBlock(DevRushTheme.strings.profileAccount) {
            AccountSettings(state, onEvent)
        }

        Gap(24)

        SettingsBlock(DevRushTheme.strings.profilePasswordSettings) {
            PasswordSettings(state, onEvent)
        }

        Gap(24)

        SettingsBlock(DevRushTheme.strings.profileAppSettings) {
            AppSettings(state, onEvent)
        }

        Gap(30)

        OutlinedColoredButton(
            DevRushTheme.strings.profileLogout,
            color = DevRushTheme.colors.blue1,
            icon = vectorResource(Res.drawable.ic_logout),
            addFraming = true
        ) { onEvent(ProfileSettingsEvent.Logout) }

        Gap(15)

        OutlinedColoredButton(
            DevRushTheme.strings.profileDeleteAccount,
            color = DevRushTheme.colors.baseRed,
            icon = vectorResource(Res.drawable.ic_trash_bin)
        ) { onEvent(ProfileSettingsEvent.DeleteAccount) }

        Gap(20)
    }
}