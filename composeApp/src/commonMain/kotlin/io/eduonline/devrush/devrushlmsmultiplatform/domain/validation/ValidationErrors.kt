package io.eduonline.devrush.devrushlmsmultiplatform.domain.validation


sealed interface ValidationErrors {
    object IsEmptyEmail : ValidationErrors
    object IsValidEmail : ValidationErrors
    object IsEmptyPassword : ValidationErrors
    object IsShortPassword : ValidationErrors
    object IsLongPassword : ValidationErrors
    object Authentication : ValidationErrors
    object ForgotPasswordErrorResponse : ValidationErrors
    object SuccessSendForgotPassword : ValidationErrors
    object IsValidName:ValidationErrors
    object IsValidConfirmPassword:ValidationErrors
    object IsEmptyField:ValidationErrors
}