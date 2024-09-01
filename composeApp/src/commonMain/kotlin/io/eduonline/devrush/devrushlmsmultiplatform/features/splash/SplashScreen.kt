package io.eduonline.devrush.devrushlmsmultiplatform.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.logo_devrush
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.di.viewModels
import io.eduonline.devrush.devrushlmsmultiplatform.features.splash.models.SplashAction
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject
import org.koin.core.context.loadKoinModules

class SplashScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: SplashViewModel = koinInject<SplashViewModel>()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow

        val loginScreen = rememberScreen(AppScreensProviders.Login)
        val mainScreen = rememberScreen(AppScreensProviders.Main)
        val onboardingScreen = rememberScreen(AppScreensProviders.OnboardingScreen)

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(264.dp),
                    imageVector = vectorResource(Res.drawable.logo_devrush),
                    contentDescription = null,
                )

                Gap(35)

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = DevRushTheme.strings.devrushCommunity,
                    style = DevRushTheme.typography.sfProBold36,
                    color = DevRushTheme.colors.c1,
                    textAlign = TextAlign.Center
                )

                Gap(19)

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = DevRushTheme.strings.urPath,
                    style = DevRushTheme.typography.sfPro16,
                    color = DevRushTheme.colors.c2,
                    textAlign = TextAlign.Center
                )
            }

        }

        when (viewAction) {
            SplashAction.ShowLoginScreen -> {
                LaunchedEffect(Unit) {
                    delay(2000)
                    navigator.replaceAll(loginScreen)
                    viewModel.clearAction()
                }
            }

            SplashAction.ShowMainScreen -> {
                LaunchedEffect(Unit) {
                    delay(2000)
                    navigator.replaceAll(mainScreen)
                    viewModel.clearAction()
                }
            }

            null -> {}
            SplashAction.ShowOnboardingScreen -> {
                LaunchedEffect(Unit) {
                    delay(2000)
                    navigator.replaceAll(onboardingScreen)
                    viewModel.clearAction()
                }
            }
        }
    }
}