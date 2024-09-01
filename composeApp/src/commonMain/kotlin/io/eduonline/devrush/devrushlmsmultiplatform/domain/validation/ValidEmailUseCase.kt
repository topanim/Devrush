package io.eduonline.devrush.devrushlmsmultiplatform.domain.validation

class ValidEmailUseCase {

   operator fun invoke(login: String): ValidationErrors? {

        val emailRegex = "[a-zA-Z0-9+._%\\-]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        return when {
            login.isEmpty() -> ValidationErrors.IsEmptyEmail
            !login.matches(emailRegex.toRegex()) -> ValidationErrors.IsValidEmail
            else -> null
        }

    }
}
