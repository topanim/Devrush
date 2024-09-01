package io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.models

sealed interface OnboardingAction {
    data object CloseScreen : OnboardingAction
}