package io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.ui.components.models

sealed interface OnboardingPageType {
    data object CenterOriented : OnboardingPageType
    data object TopOriented : OnboardingPageType
}