package io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors


data class ForgotViewState(
    val email: String = "",
    val isError: ValidationErrors? = null,
    val isErrorEmail: ValidationErrors? = null,
    val fetchEnterInfoRequest: ForgotLoadingState = ForgotLoadingState.Loading,
    val buttonEnable: Boolean = true,
    val showSnackBar: Boolean = true,
)

sealed interface ForgotLoadingState {
    data object Loading : ForgotLoadingState
    data object Success : ForgotLoadingState
    data class Error(val massage: String) : ForgotLoadingState
}

