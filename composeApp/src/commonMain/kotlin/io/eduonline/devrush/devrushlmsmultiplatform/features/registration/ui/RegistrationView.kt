package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui

import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.CustomButton
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterViewState
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.DividerElement
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.RegisterTopBar
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.TextBlock
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.TextBlockPolicyAndRules
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.TextFieldComponents.ConfirmPasswordTextField
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.TextFieldComponents.EmailTextField
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.TextFieldComponents.GenderField
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.TextFieldComponents.NameTextField
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.TextFieldComponents.PasswordTextField
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun RegistrationView(
    viewState: RegisterViewState,
    focus: FocusManager,
    onEvent: (RegisterEvent) -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical).asPaddingValues())
        .padding(vertical = 21.dp, horizontal = 20.dp)
        .pointerInput(true)
        {
            detectTapGestures(
                onTap = {
                    focus.clearFocus()
                }
            )
        }
) {
    RegisterTopBar()

    Gap(11)
    //Name
    NameTextField(viewState, onEvent)
    Gap(10)
    //Email
    EmailTextField(viewState, onEvent)
    //Password
    Gap(10)
    PasswordTextField(viewState, onEvent)
    //ConfirmPassword
    Gap(10)
    ConfirmPasswordTextField(viewState, onEvent)
    Gap(10)
    GenderField(onEvent)

    Gap(20)
    //Button Registration
    CustomButton(buttonText = DevRushTheme.strings.registrationEnterReg, progressBar = {
        CircularProgressIndicator(
            color = DevRushTheme.colors.baseWhite,
            modifier = Modifier.size(25.dp)
        )
    }, onClick = {
        onEvent.invoke(RegisterEvent.RegistrationClicked)

    })

    Gap(16)
    //Confidentiality
    TextBlockPolicyAndRules(viewState) {
        onEvent.invoke(RegisterEvent.bottomSheetText(it))
    }

    Gap(40)
    DividerElement()
    Gap(40)
    //Do you have an account?
    TextBlock {
        onEvent.invoke(RegisterEvent.EnterClicked)
    }
}





