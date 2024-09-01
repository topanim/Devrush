package io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation

import androidx.lifecycle.viewModelScope
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.AuthModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.forgotPassword.ForgotPasswordRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidEmailUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.domain.ForgotPasswordUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model.ForgotAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model.ForgotEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model.ForgotLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model.ForgotViewState
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent

class ForgotPasswordViewModel(
    private val api: AuthModule,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val validEmailUseCase: ValidEmailUseCase,
) : BaseViewModel<ForgotViewState, ForgotAction, ForgotEvent>(ForgotViewState()),
    KoinComponent {

    override fun obtainEvent(viewEvent: ForgotEvent) {
        when (viewEvent) {
            is ForgotEvent.InputTextState -> changedText(viewEvent.text)
            ForgotEvent.SendButton -> sendRequest()
            ForgotEvent.CloseScreen -> viewAction = ForgotAction.CloseScreen
            ForgotEvent.ValidEmail -> isValidEmail()
            ForgotEvent.SnackBarShow -> viewAction = ForgotAction.ShowSnackBar
        }
    }

    private fun changedText(text: String) {
        viewState = viewState.copy(email = text)
    }

    private fun sendRequest() {
        updateFetchState(ForgotLoadingState.Loading)
        safeExecute(
            scope = viewModelScope,
            block = {
                val response = api.forgotPassword(ForgotPasswordRequest(viewState.email))
                updateFetchState(ForgotLoadingState.Success)
                viewState = viewState.copy(
                    isError = forgotPasswordUseCase.execute(!response.success),
                    isErrorEmail = if (!response.success) ValidationErrors.ForgotPasswordErrorResponse else ValidationErrors.SuccessSendForgotPassword
                )
                if (viewState.isError == ValidationErrors.SuccessSendForgotPassword) {
                    ForgotEvent.SendButton
                }
            }) {

            updateFetchState(ForgotLoadingState.Error(it.toString()))
            viewAction = ForgotAction.ShowSnackBar
            delay(3000)
            enableButton(true)
        }
    }

    private fun updateFetchState(state: ForgotLoadingState) {
        viewState = viewState.copy(fetchEnterInfoRequest = state)

        viewState = when (viewState.fetchEnterInfoRequest) {
            is ForgotLoadingState.Error -> viewState.copy(buttonEnable = false)
            ForgotLoadingState.Loading -> viewState.copy(buttonEnable = false)
            ForgotLoadingState.Success -> viewState.copy(buttonEnable = true)
        }
    }

    private fun enableButton(enable: Boolean) {
        viewState = viewState.copy(buttonEnable = enable)
    }

    private fun isValidEmail() {
        viewState = viewState.copy(
            isErrorEmail = validEmailUseCase(viewState.email)
        )
    }
}