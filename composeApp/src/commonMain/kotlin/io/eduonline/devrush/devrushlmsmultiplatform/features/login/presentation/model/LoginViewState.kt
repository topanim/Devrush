package io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors

data class LoginViewState(
    val login: String = "",
    val password: String = "",
    val isVisible: Boolean = true,
    val isErrorLogin: ValidationErrors? = null,
    val isErrorPassword: ValidationErrors? = null,
    val isErrorAuthentication: Boolean = false,
    val fetchEnterInfoRequest: EnterLoadingState = EnterLoadingState.Loading,
    val buttonEnable: Boolean = true,
    val showSnackBar: Boolean = true,
)

sealed interface EnterLoadingState {
    data object Loading : EnterLoadingState
    data object Success : EnterLoadingState
    data class Error(val massage: String) : EnterLoadingState
}