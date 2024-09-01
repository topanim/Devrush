package io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation

import androidx.lifecycle.viewModelScope
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.AppSettingsRepository
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.models.OnboardingAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.models.OnboardingEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.models.OnboardingState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class OnboardingViewModel : BaseViewModel<OnboardingState, OnboardingAction, OnboardingEvent>(
    OnboardingState()
), KoinComponent {

    private val settingsRepository: AppSettingsRepository by inject()

    override fun obtainEvent(viewEvent: OnboardingEvent) {
        when (viewEvent) {
            OnboardingEvent.FinishOnboarding -> finish()
            OnboardingEvent.SkipOnboarding -> skip()
            OnboardingEvent.NextPage -> next()
            is OnboardingEvent.ChangePage -> changePage(viewEvent.page)
        }
    }

    private fun finish() {
        viewModelScope.launch { settingsRepository.updateSettings { isShowingOnboarding = true } }
        viewAction = OnboardingAction.CloseScreen
    }

    private fun skip() {
        finish()
    }

    private fun changePage(page: Int) {
        viewState = viewState.copy(
            currentPage = page,
            isLastPage = page == viewState.pageCount - 1
        )
    }

    private fun next() {
        val nextPage = viewState.currentPage + 1
        if (nextPage < viewState.pageCount) viewState = viewState.copy(
            currentPage = nextPage,
            isLastPage = nextPage == viewState.pageCount - 1
        )
        else finish()
    }
}
