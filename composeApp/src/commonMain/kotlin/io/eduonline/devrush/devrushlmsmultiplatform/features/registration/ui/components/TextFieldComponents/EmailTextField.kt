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
fun EmailTextField(state: RegisterViewState, onEvent: (RegisterEvent) -> Unit) = Column {
    val placeholder = DevRushTheme.strings.authorizationEnterYourEmailPlaceholder
    val errorText = DevRushTheme.strings.authorizationErrorLoginTextField
    val title = DevRushTheme.strings.registrationEmailTitle
    Text(
        text = title,
        color = DevRushTheme.colors.c2
    )
    Gap(5)
    CustomTextField(
        text = state.email,
        onTextChanged = {
            onEvent.invoke(RegisterEvent.InputRegistrationEmail(it))
        },
        placeholder = placeholder,
        isError = state.isErrorEmail != null,
        onFocusIsOff = {
            onEvent.invoke(RegisterEvent.ValidEmail(state.email))
        }
    )
    if (state.isErrorEmail != null) {
        Text(
            errorText,
            fontSize = 12.sp,
            color = DevRushTheme.colors.baseRed
        )
    }
}