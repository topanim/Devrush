package io.eduonline.devrush.devrushlmsmultiplatform.base

abstract class AppException(message: String? = null) : Exception(message)

object ExpiredTokenException : AppException()
