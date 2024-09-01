package io.eduonline.devrush.devrushlmsmultiplatform.features.splash

import androidx.lifecycle.viewModelScope
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.AppSettingsRepository
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.domain.IsUserAuthorizedUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.splash.models.SplashAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashViewModel(
    private val isUserAuthorizedUseCase: IsUserAuthorizedUseCase,
) : BaseViewModel<Unit, SplashAction, Unit>(
    initialState = Unit
), KoinComponent {

    private val settingsRepository: AppSettingsRepository by inject()

    init {
        checkUserAuthorized()
    }

    override fun obtainEvent(viewEvent: Unit) {
        TODO("Not yet implemented")
    }

    private fun checkUserAuthorized() = viewModelScope.launch(Dispatchers.IO) {
        val isShowOnboarding = settingsRepository.getSettings().isShowingOnboarding

        viewAction = if (isShowOnboarding) {
            if (isUserAuthorizedUseCase()) {
                SplashAction.ShowMainScreen
            } else {
                SplashAction.ShowLoginScreen
            }
        } else {
            SplashAction.ShowOnboardingScreen
        }
    }
}