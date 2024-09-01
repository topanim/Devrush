package io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.models

data class OnboardingState(
    val currentPage: Int = 0,
    val isLastPage: Boolean = false,
    val pageCount: Int = 4,
)