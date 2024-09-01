package io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model

sealed class LoginAction {
    object PresentMainScreen : LoginAction()
    object PresentRegistrationScreen : LoginAction()
    object EnterGoogle : LoginAction()
    object ForgotPassword : LoginAction()
    object ShowSnackBar : LoginAction()
}