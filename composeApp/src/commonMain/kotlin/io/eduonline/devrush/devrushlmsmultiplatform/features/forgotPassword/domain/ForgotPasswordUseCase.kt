package io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.domain

import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors

class ForgotPasswordUseCase {

    fun execute(send: Boolean = false): ValidationErrors? {
        return if (send) ValidationErrors.SuccessSendForgotPassword else null
    }
}