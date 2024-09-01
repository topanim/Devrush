package io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.OnboardingViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.models.OnboardingAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.ui.OnboardingView
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import org.koin.compose.koinInject

class OnboardingScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: OnboardingViewModel = koinInject<OnboardingViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)

        OnboardingView(viewState) { event ->
            viewModel.obtainEvent(event)
        }

        when (viewAction) {
            null -> {}
            OnboardingAction.CloseScreen -> {
                navigator.replaceAll(rememberScreen(AppScreensProviders.Login))
            }
        }
    }
}