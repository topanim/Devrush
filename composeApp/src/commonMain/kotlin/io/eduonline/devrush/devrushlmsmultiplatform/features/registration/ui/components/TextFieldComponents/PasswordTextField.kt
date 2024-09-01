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
fun PasswordTextField(state: RegisterViewState, onEvent:(RegisterEvent) -> Unit)= Column {
    val placeholder = DevRushTheme.strings.registrationPasswordTitle
    val errorMin = DevRushTheme.strings.registrationIsErrorMinLengthPassword
    val errorMax = DevRushTheme.strings.registrationIsErrorMaxLengthPassword
    Text(
        text = placeholder,
        color = DevRushTheme.colors.c2
    )
    Gap(5)
    CustomTextField(
        text = state.password,
        onTextChanged = {
           onEvent(RegisterEvent.InputPasswordTextState(it))
        },
        placeholder = placeholder,
        isError = state.isErrorPassword != null,
        onFocusIsOff = {
            onEvent(RegisterEvent.ValidPassword(state.password))
        }
    )
    if (state.isErrorPassword != null) {
        Text(
            when {
                state.password.length < 6 -> errorMin
                state.password.length > 30 -> errorMax
                else -> ""
            },
            fontSize = 12.sp,
            color = DevRushTheme.colors.baseRed
        )
    }
}