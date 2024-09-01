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
fun NameTextField(state: RegisterViewState, onEvent: (RegisterEvent) -> Unit) = Column {
    val title = DevRushTheme.strings.registrationName
    val errorText = DevRushTheme.strings.registrationIsErrorName
    val placeholder = DevRushTheme.strings.registrationNamePlaceholder
    Text(
        text = title,
        color = DevRushTheme.colors.c2
    )
    Gap(5)
    CustomTextField(
        text = state.firstName ?: "",
        onTextChanged = {
            onEvent(RegisterEvent.InputRegistrationName(it))
        },
        placeholder = placeholder,
        isError = state.isErrorName != null,
        onFocusIsOff = {
            onEvent(RegisterEvent.ValidName(state.firstName?:""))
        }
    )
    if (state.isErrorName != null) {
        Text(
            errorText,
            fontSize = 12.sp,
            color = DevRushTheme.colors.baseRed
        )
    }
}