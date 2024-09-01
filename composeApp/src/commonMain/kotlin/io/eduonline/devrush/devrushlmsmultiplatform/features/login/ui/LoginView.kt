package io.eduonline.devrush.devrushlmsmultiplatform.features.login.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.CustomButton
import io.eduonline.devrush.devrushlmsmultiplatform.components.CustomTextField
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model.LoginEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model.LoginViewState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun LoginView(viewState: LoginViewState, onEvent: (LoginEvent) -> Unit) {
    val focus = LocalFocusManager.current
    var emailTextFieldFocus by remember { mutableStateOf(false) }
    var passwordTextFieldFocus by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical).asPaddingValues())
            .pointerInput(viewState.isErrorLogin)
            {
                detectTapGestures(
                    onTap = {
                        focus.clearFocus()
                    }
                )
            }
    ) {
        Gap(20)
        DevRushTopAppBar(
            title = DevRushTheme.strings.authorizationTitle,
            backButton = false,
            style = DevRushTheme.typography.sfProBold36,
            alignment = Alignment.TopStart,
            clipText = false,
        )
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 12.dp)) {
            Text(
                text = DevRushTheme.strings.authorizationEnterYourEmail,
                style = DevRushTheme.typography.sfPro14,
                color = DevRushTheme.colors.c2,
            )
            Gap(5)
            CustomTextField(
                placeholder = DevRushTheme.strings.authorizationEnterYourEmailPlaceholder,
                keyboardType = KeyboardType.Email,
                text = viewState.login,
                onTextChanged = {
                    onEvent.invoke(LoginEvent.InputLoginTextState(it))
                },
                isError = viewState.isErrorLogin != null,
                onFocusIsOff = {
                    if (emailTextFieldFocus) {
                        onEvent.invoke(LoginEvent.ValidLogin)
                    }
                    emailTextFieldFocus = true
                }
            )
            Gap(5)
            if (viewState.isErrorLogin != null) {
                Text(
                    text = when (viewState.isErrorLogin) {
                        ValidationErrors.IsEmptyEmail -> DevRushTheme.strings.authorizationErrorLoginEmptyTexField
                        ValidationErrors.IsValidEmail -> DevRushTheme.strings.authorizationErrorLoginTextField
                        else -> ""
                    },
                    color = DevRushTheme.colors.baseRed,
                    style = DevRushTheme.typography.sfPro12
                )
            }
            Gap(5)
            Text(
                text = DevRushTheme.strings.authorizationPassword,
                style = DevRushTheme.typography.sfPro14,
                color = DevRushTheme.colors.c2,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            CustomTextField(
                text = viewState.password,
                onTextChanged = {
                    onEvent.invoke(LoginEvent.InputPasswordTextState(it))
                },
                placeholder = DevRushTheme.strings.authorizationPasswordPlaceholder,
                showTrailingIcon = true,
                isTextVisibility = viewState.isVisible,
                onHideText = {
                    onEvent.invoke(LoginEvent.TogglePasswordVisibility)
                },
                isError = if (viewState.isErrorPassword == null) false else true,
                onFocusIsOff = {
                    if (passwordTextFieldFocus) {
                        onEvent.invoke(LoginEvent.ValidPassword)
                    }
                    passwordTextFieldFocus = true
                }
            )
            Gap(5)
            if (viewState.isErrorPassword != null) {
                Text(
                    text = when (viewState.isErrorPassword) {
                        ValidationErrors.IsEmptyPassword -> DevRushTheme.strings.authorizationErrorPasswordEmptyTextField
                        ValidationErrors.IsLongPassword -> DevRushTheme.strings.authorizationErrorPasswordMaxTextField
                        ValidationErrors.IsShortPassword -> DevRushTheme.strings.authorizationErrorPasswordMinTextField
                        ValidationErrors.Authentication -> DevRushTheme.strings.authorizationErrorTextField
                        else -> ""
                    },
                    style = DevRushTheme.typography.sfPro12,
                    color = DevRushTheme.colors.baseRed
                )
            }
            Gap(16)

            CustomButton(
                buttonText = DevRushTheme.strings.authorizationEnterButton,
                enable = viewState.buttonEnable,
                progressBar = {
                    CircularProgressIndicator(
                        color = DevRushTheme.colors.baseWhite,
                        modifier = Modifier.size(25.dp)
                    )
                },
                onClick = {
                    onEvent.invoke(LoginEvent.EnterClicked)
                })

            Gap(16)

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = DevRushTheme.strings.authorizationForgotPassword,
                    textAlign = TextAlign.Center,
                    style = DevRushTheme.typography.sfProBold14,
                    color = DevRushTheme.colors.blue1,
                    modifier = Modifier
                        .clickable(enabled = viewState.buttonEnable) {
                            onEvent.invoke(LoginEvent.ForgotPasswordClicked)
                        },
                )
            }
            Gap(65)
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                HorizontalDivider(
                    Modifier.fillMaxWidth(0.46f),
                    color = DevRushTheme.colors.c4
                )
                Text(
                    text = DevRushTheme.strings.authorizationWordBetweenDivider,
                    style = DevRushTheme.typography.sfPro12,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = DevRushTheme.colors.c4
                )
                HorizontalDivider(color = DevRushTheme.colors.c4)
            }
            Gap(45)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = DevRushTheme.strings.authorizationNoAccount,
                    style = DevRushTheme.typography.sfPro12,
                    color = DevRushTheme.colors.c2
                )
                Text(
                    text = DevRushTheme.strings.authorizationButtonToRegistration,
                    style = DevRushTheme.typography.sfProBold12,
                    color = DevRushTheme.colors.blue1,
                    modifier = Modifier.clickable(enabled = viewState.buttonEnable) {
                        onEvent.invoke(LoginEvent.RegistrationClicked)
                    }
                )
            }
        }
    }
}

