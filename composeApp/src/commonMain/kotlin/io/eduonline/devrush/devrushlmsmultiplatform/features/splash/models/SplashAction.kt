package io.eduonline.devrush.devrushlmsmultiplatform.features.splash.models

sealed class SplashAction {
    data object ShowMainScreen : SplashAction()
    data object ShowLoginScreen : SplashAction()
    data object ShowOnboardingScreen : SplashAction()
}