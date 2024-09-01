package io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword

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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.ForgotPasswordViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.model.ForgotAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.ui.ForgotView
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class ForgotPasswordScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: ForgotPasswordViewModel = koinInject<ForgotPasswordViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val scope = rememberCoroutineScope()
        val snackBarHostState = remember { SnackbarHostState() }
        val snackMassage = DevRushTheme.strings.authorizationNoInternet

        ForgotView(viewState) { event ->
            viewModel.obtainEvent(event)
        }

        when (viewAction) {
            ForgotAction.CloseScreen -> {
                navigator.pop()
                viewModel.clearAction()
            }

            ForgotAction.ShowSnackBar -> Box(
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

            null -> {}

        }
    }
}