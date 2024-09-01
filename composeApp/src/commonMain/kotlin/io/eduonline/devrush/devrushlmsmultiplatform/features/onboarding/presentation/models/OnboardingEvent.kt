package io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.models

sealed interface OnboardingEvent {
    data object FinishOnboarding : OnboardingEvent
    data object SkipOnboarding : OnboardingEvent
    data object NextPage : OnboardingEvent
    class ChangePage(val page: Int) : OnboardingEvent
}