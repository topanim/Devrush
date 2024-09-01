package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusManager
import cafe.adriel.voyager.navigator.Navigator
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileSettingsTopBar(
    state: ProfileSettingsState,
    navigator: Navigator,
    focusManager: FocusManager,
    onBackPress: () -> Unit,
    onEvent: (ProfileSettingsEvent) -> Unit,
) = DevRushTopAppBar(
    DevRushTheme.strings.profileSettings,
    style = DevRushTheme.typography.sfProBold16
        .copy(color = DevRushTheme.colors.c6),
    trailing = {
        if (state.isProfileEditing)
            TextButton(
                modifier = it,
                onClick = {
                    if (state.fields!!.password != state.fields.confirmPassword) {
                        onEvent(ProfileSettingsEvent.ErrorCompare)
                    } else {
                        focusManager.clearFocus()
                        onEvent(ProfileSettingsEvent.Save())
                    }
                }
            ) {
                Text(
                    DevRushTheme.strings.profileDone,
                    color = DevRushTheme.colors.c1,
                    style = DevRushTheme.typography.sfProBold16
                )
            }
    }
) { onBackPress() }