package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model

sealed class RegisterAction {
    object PresentMainScreen : RegisterAction()
    object PresentLoginScreen : RegisterAction()
    object ShowSnackBar : RegisterAction()
    //object EnterGoogle : RegisterAction()
    //object TogglePasswordVisibility : RegisterAction()

}