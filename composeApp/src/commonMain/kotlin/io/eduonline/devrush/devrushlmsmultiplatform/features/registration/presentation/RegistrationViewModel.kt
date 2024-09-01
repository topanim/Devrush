package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.AuthModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.register.RegisterRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.AllValidation
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.domain.SaveNameProfile
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.domain.SaveNameProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterViewState
import org.koin.core.component.KoinComponent

class RegistrationViewModel(
    private val api: AuthModule,
    private val saveProfileUseCase: SaveNameProfileUseCase,
    private val authRepository: AuthTokenRepository,
    private val validations: AllValidation
) :
    BaseViewModel<RegisterViewState, RegisterAction, RegisterEvent>(RegisterViewState()),
    KoinComponent {


    override fun obtainEvent(viewEvent: RegisterEvent) {
        when (viewEvent) {
            RegisterEvent.RegistrationClicked -> {
                checkValidInputData()
            }

            is RegisterEvent.InputRegistrationName -> onChangedRegName(viewEvent.text)
            is RegisterEvent.InputRegistrationEmail -> onChangedRegEmail(viewEvent.text)
            is RegisterEvent.InputPasswordTextState -> onChangedPasswordText(viewEvent.text)
            is RegisterEvent.InputConfiderPasswordTextState -> onChangedConfirmPasswordText(
                viewEvent.text
            )

            RegisterEvent.EnterClicked -> viewAction = RegisterAction.PresentLoginScreen
            is RegisterEvent.bottomSheetText -> setTextBottomSheet(viewEvent.text)
            is RegisterEvent.ValidConfirmPassword -> {
                viewState = viewState.copy(
                    isErrorConfirmPassword = validations.validConfirmPassword(
                        viewState.password,
                        viewState.confirmPassword
                    )
                )
            }

            is RegisterEvent.ValidEmail -> {
                viewState =
                    viewState.copy(isErrorEmail = validations.validInputEmail(viewState.email))
            }

            is RegisterEvent.ValidName -> {
                viewState = viewState.copy(isErrorName = viewState.firstName.let {
                    validations.validName(
                        it
                    )
                })
            }

            is RegisterEvent.ValidPassword -> {
                viewState =
                    viewState.copy(isErrorPassword = validations.validPassword(viewState.password))
            }

            is RegisterEvent.SetGender -> viewState = viewState.copy(gender = viewEvent.gender)
        }
    }

    private fun setTextBottomSheet(text: String) {
        viewState = viewState.copy(bottomSheetText = text)
    }


    private fun sendRegistrationRequest() {
        safeExecute(
            scope = viewModelScope,
            block = {
                val registerResponse = api.register(
                    RegisterRequest(
                        email = viewState.email,
                        password = viewState.password,
                        confirmPassword = viewState.confirmPassword,
                        lang = Locale.current.language,
                        timezoneOffset = "+0300",
                    )
                )
                Logger.i(tag = "gender", messageString = viewState.gender)
                if (registerResponse.success) {
                    authRepository.saveTokens(
                        registerResponse.body!!.accessToken,
                        registerResponse.body.refreshToken
                    )
                    saveProfileUseCase(
                        SaveNameProfile(viewState.email,viewState.firstName,viewState.gender,)
                    )
                    viewAction = RegisterAction.PresentMainScreen
                } else {
                   showSnackBar(registerResponse.errors[0].message)
                    viewAction = RegisterAction.ShowSnackBar
                }
            }
        ) {
        }
    }
    private fun showSnackBar(massage:String){
        viewState = viewState.copy(showSnackBar = massage)
    }

    private fun onChangedPasswordText(text: String) {
        viewState = viewState.copy(password = text)

    }

    private fun onChangedConfirmPasswordText(text: String) {
        viewState = viewState.copy(confirmPassword = text)
    }

    private fun onChangedRegName(text: String) {
        viewState = viewState.copy(firstName = text)
    }

    private fun onChangedRegEmail(text: String) {
        viewState = viewState.copy(email = text)
    }

    private fun checkValidInputData() {
        when {
            viewState.firstName?.isEmpty() == true -> viewState =
                viewState.copy(isErrorName = ValidationErrors.IsEmptyField)

            viewState.email.isEmpty() -> viewState =
                viewState.copy(isErrorEmail = ValidationErrors.IsEmptyField)

            viewState.password.isEmpty() -> viewState =
                viewState.copy(isErrorPassword = ValidationErrors.IsEmptyField)

            viewState.confirmPassword.isEmpty() -> viewState =
                viewState.copy(isErrorConfirmPassword = ValidationErrors.IsEmptyField)
        }
        if (viewState.isErrorConfirmPassword == null
            && viewState.isErrorEmail == null
            && viewState.isErrorName == null
            && viewState.isErrorPassword == null
        ) {
            sendRegistrationRequest()
        }


    }
}
