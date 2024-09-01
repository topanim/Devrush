package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors

data class RegisterViewState(
    val firstName: String = "",
    val email: String = "",
    val password: String = "",
    val gender: String = "male",
    val confirmPassword: String = "",
    val isErrorName: ValidationErrors? = null,
    val isErrorEmail: ValidationErrors? = null,
    val isErrorPassword: ValidationErrors? = null,
    val isErrorConfirmPassword: ValidationErrors? = null,
    val bottomSheetText: String = "",
    val showSnackBar: String = ""
)