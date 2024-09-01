package io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.CustomButton
import io.eduonline.devrush.devrushlmsmultiplatform.components.CustomTextField
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model.ForgotEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model.ForgotViewState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ForgotView(state: ForgotViewState, onEvent: (ForgotEvent) -> Unit) {
    val modifier = Modifier
    val focus = LocalFocusManager.current
    var textFieldFocus by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize()
            .padding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical).asPaddingValues())
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focus.clearFocus() }
                )
            },
    ) {
        DevRushTopAppBar(
            title = DevRushTheme.strings.forgetPasswordTitle,
            style = DevRushTheme.typography.sfProBold24,
            onBackClick = { onEvent.invoke(ForgotEvent.CloseScreen) }
        )
        Column(modifier = modifier.padding(horizontal = 20.dp)) {
            Gap(26)
            Text(
                DevRushTheme.strings.forgetPasswordEnterYourEmail,
                style = DevRushTheme.typography.sfPro14,
                color = DevRushTheme.colors.c2
            )
            Gap(5)
            CustomTextField(
                text = state.email,
                onTextChanged = {
                    onEvent.invoke(ForgotEvent.InputTextState(it))
                },
                placeholder = DevRushTheme.strings.forgetPasswordEnterYourEmailPlaceholder,
                isError = state.isErrorEmail != null && state.isError != null,
                keyboardType = KeyboardType.Email,
                onFocusIsOff = {
                    if (textFieldFocus) {
                        onEvent.invoke(ForgotEvent.ValidEmail)
                    }
                    textFieldFocus = true
                },
            )
            Gap(5)
            Text(
                text = when (state.isErrorEmail) {
                    ValidationErrors.IsEmptyEmail -> DevRushTheme.strings.forgetPasswordEmptyError
                    ValidationErrors.IsValidEmail -> DevRushTheme.strings.forgetPasswordInvalidEmail
                    ValidationErrors.ForgotPasswordErrorResponse -> DevRushTheme.strings.forgetPasswordDontExist
                    ValidationErrors.SuccessSendForgotPassword -> DevRushTheme.strings.forgotSuccessSend
                    else -> ""
                },
                style = DevRushTheme.typography.sfPro12,
                color = if (state.isErrorEmail == ValidationErrors.SuccessSendForgotPassword)
                    DevRushTheme.colors.c2 else DevRushTheme.colors.baseRed
            )
            Box(
                modifier = modifier.fillMaxSize().padding(bottom = 12.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                CustomButton(
                    buttonText = DevRushTheme.strings.forgetPasswordSend,
                    enable = state.buttonEnable,
                    progressBar = {
                        CircularProgressIndicator(
                            color = DevRushTheme.colors.baseWhite,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                ) {
                    onEvent.invoke(ForgotEvent.SendButton)
                }
            }
        }
    }
}



