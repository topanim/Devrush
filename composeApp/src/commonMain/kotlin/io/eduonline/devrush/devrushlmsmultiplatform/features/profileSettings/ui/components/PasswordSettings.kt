package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ColumnScope.PasswordSettings(
    state: ProfileSettingsState,
    onEvent: (ProfileSettingsEvent) -> Unit = {},
) {
    val errorTextCompare = DevRushTheme.strings.registrationIsErrorConfirmPassword
    val errorMinLength = DevRushTheme.strings.authorizationErrorPasswordMinTextField
    val errorMaxLength = DevRushTheme.strings.authorizationErrorPasswordMaxTextField
    SettingsTextFieldBlock(
        DevRushTheme.strings.profilePassword,
        state = state.fields!!.password,
        keyboardType = KeyboardType.Password,
        placeholder = DevRushTheme.strings.profilePassword,
        hide = true,
        validator = {
            when {
                it.isEmpty() -> ValidationState.Ok
                it.length < 6 -> ValidationState.Error(errorMinLength)
                it.length > 30 -> ValidationState.Error(errorMaxLength)
                else -> ValidationState.Ok
            }
        }
    ) { onEvent(ProfileSettingsEvent.EditPassword(it)) }

    Gap(16)

    SettingsTextFieldBlock(
        DevRushTheme.strings.profileConfirmPassword,
        state = state.fields.confirmPassword,
        keyboardType = KeyboardType.Password,
        placeholder = DevRushTheme.strings.profilePassword,
        hide = true,
        validator = {
            if (it == state.fields.password || it.isEmpty()) ValidationState.Ok else ValidationState.Error(
                errorTextCompare
            )
        }
    ) { onEvent(ProfileSettingsEvent.EditConfirmPassword(it)) }
    Gap(5)

    if (state.fields.password == state.fields.confirmPassword)
        ValidationState.Ok else ValidationState.Error(errorTextCompare)

}