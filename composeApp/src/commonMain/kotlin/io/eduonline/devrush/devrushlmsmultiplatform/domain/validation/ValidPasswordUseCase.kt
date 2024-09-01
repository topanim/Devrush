package io.eduonline.devrush.devrushlmsmultiplatform.domain.validation

class ValidPasswordUseCase {

    operator fun invoke(password: String): ValidationErrors? {
        return when {
            password.isEmpty() -> ValidationErrors.IsEmptyPassword
            password.length < 6 -> ValidationErrors.IsShortPassword
            password.length > 30 -> ValidationErrors.IsLongPassword
            else -> null
        }
    }
}
