package io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.arrow_right
import devrush_multiplatform.composeapp.generated.resources.frame_01_screen_onboarding
import devrush_multiplatform.composeapp.generated.resources.frame_02_screen_onboarding
import devrush_multiplatform.composeapp.generated.resources.frame_03_screen_onboarding
import devrush_multiplatform.composeapp.generated.resources.frame_04_screen_onboarding
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.models.OnboardingEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.models.OnboardingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.ui.components.OnboardingPage
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.ui.components.models.OnboardingPageType
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingView(
    state: OnboardingState,
    onEvent: (OnboardingEvent) -> Unit,
) {
    val onSkip = remember { { onEvent(OnboardingEvent.SkipOnboarding) } }
    val pagerState = rememberPagerState(initialPage = state.currentPage) { state.pageCount }

    LaunchedEffect(state.currentPage) {
        if (pagerState.currentPage != state.currentPage) {
            pagerState.animateScrollToPage(
                page = state.currentPage,
                animationSpec = spring(stiffness = Spring.StiffnessLow)
            )
        }
    }

    val pages = listOf(
        OnboardingPage(
            image = vectorResource(Res.drawable.frame_01_screen_onboarding),
            title = DevRushTheme.strings.onboardingScreen1Title,
            description = DevRushTheme.strings.onboardingScreen1Description,
            type = OnboardingPageType.CenterOriented,
            showSkip = false,
            onSkip = {}
        ),
        OnboardingPage(
            image = vectorResource(Res.drawable.frame_02_screen_onboarding),
            title = DevRushTheme.strings.onboardingScreen2Title,
            description = DevRushTheme.strings.onboardingScreen2Description,
            type = OnboardingPageType.TopOriented,
            onSkip = onSkip
        ),
        OnboardingPage(
            image = vectorResource(Res.drawable.frame_03_screen_onboarding),
            title = DevRushTheme.strings.onboardingScreen3Title,
            description = DevRushTheme.strings.onboardingScreen3Description,
            type = OnboardingPageType.TopOriented,
            onSkip = onSkip
        ),
        OnboardingPage(
            image = vectorResource(Res.drawable.frame_04_screen_onboarding),
            title = DevRushTheme.strings.onboardingScreen4Title,
            description = DevRushTheme.strings.onboardingScreen4Description,
            type = OnboardingPageType.TopOriented,
            onSkip = onSkip
        )
    )


    Box(
        Modifier.fillMaxSize()
            .padding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom).asPaddingValues())
    ) {
        HorizontalPager(
            pagerState,
            modifier = Modifier.fillMaxSize()
        ) {
            pages[it].Content()
        }

        Button(
            onClick = { onEvent(OnboardingEvent.NextPage) },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = DevRushTheme.colors.blue1
            )
        ) {
            Box(
                Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(
                    if (state.currentPage == 0) DevRushTheme.strings.onboardingStart
                    else DevRushTheme.strings.onboardingNext,
                    style = DevRushTheme.typography.sfPro14,
                    color = DevRushTheme.colors.c6,
                    modifier = Modifier.align(Alignment.Center)
                )

                Icon(
                    imageVector = vectorResource(Res.drawable.arrow_right),
                    contentDescription = null,
                    tint = DevRushTheme.colors.c6,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        onEvent(OnboardingEvent.ChangePage(pagerState.currentPage))
    }
}