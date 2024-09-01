package io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model

sealed class ForgotAction {
    object CloseScreen : ForgotAction()
    object ShowSnackBar : ForgotAction()
}