package io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model

sealed class LoginEvent {

    class InputLoginTextState(val text: String) : LoginEvent()
    class InputPasswordTextState(val text: String) : LoginEvent()

    object TogglePasswordVisibility : LoginEvent()
    object EnterClicked : LoginEvent()
    object ForgotPasswordClicked : LoginEvent()
    object RegistrationClicked : LoginEvent()
    object EnterGoogleClicked : LoginEvent()
    object ValidLogin : LoginEvent()
    object ValidPassword : LoginEvent()
    class EnableButtons(val enable: Boolean) : LoginEvent()
    object ShowSnackBar : LoginEvent()

}