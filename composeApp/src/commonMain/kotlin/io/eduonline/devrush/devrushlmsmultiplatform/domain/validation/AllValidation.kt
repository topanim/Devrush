package io.eduonline.devrush.devrushlmsmultiplatform.domain.validation

class AllValidation {
    fun validInputEmail(text: String): ValidationErrors? {
        val emailRegex = "[a-zA-Z0-9+._%\\-]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        return when {
            text.isEmpty() -> null
            !text.matches(emailRegex.toRegex()) -> ValidationErrors.IsValidEmail
            else -> null
        }
    }

    fun validPassword(password: String): ValidationErrors? {
        return when {
            password.isEmpty() -> null
            password.length < 6 -> ValidationErrors.IsShortPassword
            password.length > 30 -> ValidationErrors.IsLongPassword
            else -> null
        }
    }

    fun validConfirmPassword(password: String, confirmPassword: String): ValidationErrors? {
        return when {
            password != confirmPassword -> ValidationErrors.IsValidConfirmPassword
            else -> null
        }
    }

    fun validName(userName: String): ValidationErrors? {
        return when{
            userName.isEmpty() -> null
            userName.length < 2 -> ValidationErrors.IsValidName
            userName.length > 15 -> ValidationErrors.IsValidName
            else -> null
        }
    }
}