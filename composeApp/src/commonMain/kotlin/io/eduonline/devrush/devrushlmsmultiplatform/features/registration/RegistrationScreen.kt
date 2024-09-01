package io.eduonline.devrush.devrushlmsmultiplatform.features.registration

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
import androidx.compose.ui.platform.LocalFocusManager
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.RegistrationViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.RegistrationView
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class RegistrationScreen : Screen {

    @Composable
    override fun Content() {

        val viewModel: RegistrationViewModel = koinInject<RegistrationViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow
        val focus = LocalFocusManager.current
        val scope = rememberCoroutineScope()
        val snackBarHostState = remember { SnackbarHostState() }

        val mainScreen = rememberScreen(AppScreensProviders.Main)
        val loginScreen = rememberScreen(AppScreensProviders.Login)


        RegistrationView(viewState,focus) { registerEvent ->
            viewModel.obtainEvent(registerEvent)
        }

        when (viewAction) {
            RegisterAction.PresentMainScreen -> {
                navigator.push(mainScreen)
                viewModel.clearAction()
            }

            RegisterAction.PresentLoginScreen -> {
                navigator.push(loginScreen)
                viewModel.clearAction()
            }
            is RegisterAction.ShowSnackBar -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(
                        WindowInsets.systemBars.only(
                            WindowInsetsSides.Vertical
                        ).asPaddingValues()
                    ), contentAlignment = Alignment.BottomCenter
                ) {
                    scope.launch {
                        snackBarHostState.showSnackbar(message = viewState.showSnackBar)
                        viewModel.clearAction()
                    }
                    SnackbarHost(snackBarHostState)
                }
            }

            null -> {}
        }
    }
}