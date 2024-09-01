package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.TextFieldComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import io.eduonline.devrush.devrushlmsmultiplatform.components.CustomTextField
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterViewState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ConfirmPasswordTextField(state: RegisterViewState, onEvent: (RegisterEvent) -> Unit) = Column {
    val title = DevRushTheme.strings.registrationConfirmPasswordTitle
    val errorText = DevRushTheme.strings.registrationIsErrorConfirmPassword
    val placeholder = DevRushTheme.strings.registrationPasswordTitle
    Text(
        text = title,
        color = DevRushTheme.colors.c2
    )
    Gap(5)
    CustomTextField(
        text = state.confirmPassword,
        onTextChanged = {
            onEvent.invoke(RegisterEvent.InputConfiderPasswordTextState(it))
        },
        placeholder = placeholder,
        isError = state.isErrorConfirmPassword != null,
        onFocusIsOff = {
            onEvent.invoke(RegisterEvent.ValidConfirmPassword(state.password,state.confirmPassword))
        }
    )
    if (state.isErrorConfirmPassword != null) {
        Text(
            errorText,
            fontSize = 12.sp,
            color = DevRushTheme.colors.baseRed
        )
    }
}