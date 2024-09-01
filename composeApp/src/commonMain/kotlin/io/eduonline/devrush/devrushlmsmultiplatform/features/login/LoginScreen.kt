package io.eduonline.devrush.devrushlmsmultiplatform.features.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.LoginViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.model.LoginAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.ui.LoginView
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinInject<LoginViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)


        LoginView(viewState) { event ->
            viewModel.obtainEvent(event)
        }
        val scope = rememberCoroutineScope()
        val snackBarHostState = remember { SnackbarHostState() }
        val mainScreen = rememberScreen(AppScreensProviders.Main)
        val registrationScreen = rememberScreen(AppScreensProviders.Registration)
        val forgotPasswordScreen = rememberScreen(AppScreensProviders.ForgotPassword)
        val snackMassage = DevRushTheme.strings.authorizationNoInternet

        when (viewAction) {
            LoginAction.EnterGoogle -> {
                navigator.push(mainScreen)
                viewModel.clearAction()
            }

            LoginAction.ForgotPassword -> {
                navigator.push(forgotPasswordScreen)
                viewModel.clearAction()
            }

            LoginAction.PresentMainScreen -> {
                navigator.replaceAll(mainScreen)
                viewModel.clearAction()
            }

            LoginAction.PresentRegistrationScreen -> {
                navigator.push(registrationScreen)
                viewModel.clearAction()
            }

            LoginAction.ShowSnackBar -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(
                        WindowInsets.systemBars.only(
                            WindowInsetsSides.Vertical
                        ).asPaddingValues()
                    ), contentAlignment = Alignment.BottomCenter
                ) {
                    scope.launch {
                        snackBarHostState.showSnackbar(message = snackMassage)
                        viewModel.clearAction()
                    }
                    SnackbarHost(snackBarHostState)
                }

            }

            null -> {}
        }
    }
}