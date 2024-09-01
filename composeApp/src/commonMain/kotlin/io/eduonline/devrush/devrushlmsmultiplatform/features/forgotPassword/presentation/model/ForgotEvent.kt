package io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model

sealed class ForgotEvent {
    class InputTextState(val text: String) : ForgotEvent()
    object CloseScreen : ForgotEvent()
    object SendButton : ForgotEvent()
    object ValidEmail : ForgotEvent()
    object SnackBarShow : ForgotEvent()
}