package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model

sealed class RegisterEvent {
    class InputRegistrationName(val text: String) : RegisterEvent()
    class InputRegistrationEmail(val text: String) : RegisterEvent()
    class InputPasswordTextState(val text: String) : RegisterEvent()
    class InputConfiderPasswordTextState(val text: String) : RegisterEvent()
    data class SetGender(val gender:String):RegisterEvent()
    data class ValidEmail(val text: String) : RegisterEvent()
    data class ValidName(val text: String) : RegisterEvent()
    data class ValidPassword(val text: String) : RegisterEvent()
    data class ValidConfirmPassword(val text: String,val confirm:String) : RegisterEvent()
    object RegistrationClicked : RegisterEvent()
    object EnterClicked : RegisterEvent()
    data class bottomSheetText(val text: String) : RegisterEvent()

}