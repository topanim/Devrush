package io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation

import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.AuthModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.login.LoginRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidEmailUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidPasswordUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.domain.IsUserAuthorizedUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model.EnterLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model.LoginAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model.LoginEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model.LoginViewState
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent

class LoginViewModel(
    private val api: AuthModule,
    private val authRepository: AuthTokenRepository,
    private val isUserAuthorizedUseCase: IsUserAuthorizedUseCase,
    private val validEmailUseCase: ValidEmailUseCase,
    private val validPasswordUseCase: ValidPasswordUseCase,
) : BaseViewModel<LoginViewState, LoginAction, LoginEvent>(LoginViewState()), KoinComponent {

    override fun obtainEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            LoginEvent.EnterClicked -> sendLoginRequest()
            LoginEvent.EnterGoogleClicked -> viewAction = LoginAction.EnterGoogle
            LoginEvent.ForgotPasswordClicked -> viewAction = LoginAction.ForgotPassword
            is LoginEvent.InputLoginTextState -> onChangedLoginText(viewEvent.text)
            LoginEvent.RegistrationClicked -> viewAction = LoginAction.PresentRegistrationScreen
            LoginEvent.TogglePasswordVisibility -> onChangedIcon()
            is LoginEvent.InputPasswordTextState -> onChangedPasswordText(viewEvent.text)
            LoginEvent.ValidLogin -> isValidEmail()
            LoginEvent.ValidPassword -> isValidPassword()
            is LoginEvent.EnableButtons -> buttonEnable(viewEvent.enable)
            LoginEvent.ShowSnackBar -> viewAction = LoginAction.ShowSnackBar
        }
    }

    private fun sendLoginRequest() {
        updateFetchState(EnterLoadingState.Loading)
        safeExecute(
            scope = viewModelScope,
            block = {
                val response = api.login(
                    LoginRequest(
                        viewState.login,
                        viewState.password,
                        true
                    )
                )

                val responseAnswer = isUserAuthorizedUseCase()

                Logger.i(tag = "responce", messageString = responseAnswer.toString())
                viewState = viewState.copy(
                    isErrorLogin = if (!response.success) ValidationErrors.Authentication else null,
                    isErrorAuthentication = !response.success,
                    isErrorPassword = if (!response.success) ValidationErrors.Authentication else null,
                )
                updateFetchState(EnterLoadingState.Success)
                if (!viewState.isErrorAuthentication) {
                    authRepository.saveTokens(
                        response.body!!.accessToken,
                        response.body.refreshToken
                    )
                    viewAction = LoginAction.PresentMainScreen
                }
            }) {
            updateFetchState(EnterLoadingState.Error(it.toString()))
            delay(3000)
            buttonEnable(true)
        }

    }

    private fun updateFetchState(state: EnterLoadingState) {
        viewState = viewState.copy(fetchEnterInfoRequest = state)

        viewState = when (viewState.fetchEnterInfoRequest) {
            is EnterLoadingState.Error -> viewState.copy(buttonEnable = false)
            EnterLoadingState.Loading -> viewState.copy(buttonEnable = false)
            EnterLoadingState.Success -> viewState.copy(buttonEnable = true)
        }
    }

    private fun buttonEnable(enable: Boolean) {
        viewState = viewState.copy(buttonEnable = enable)
    }


    private fun onChangedLoginText(text: String) {
        viewState = viewState.copy(login = text)
    }

    private fun onChangedPasswordText(text: String) {
        viewState = viewState.copy(password = text)
    }

    private fun onChangedIcon() {
        viewState = viewState.copy(isVisible = !viewState.isVisible)
    }

    private fun isValidEmail() {
        viewState = viewState.copy(
            isErrorLogin = validEmailUseCase(viewState.login)
        )

    }

    private fun isValidPassword() {
        viewState = viewState.copy(isErrorPassword = validPasswordUseCase(viewState.password))
    }


}