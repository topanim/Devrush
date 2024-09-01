package io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.ui.components.models.OnboardingPageType
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme


class OnboardingPage(
    private val image: ImageVector,
    private val title: String,
    private val description: String,
    private val type: OnboardingPageType = OnboardingPageType.TopOriented,
    private val showSkip: Boolean = true,
    private val onSkip: () -> Unit,
) {
    companion object {
        private const val backgroundIndex = 1f
        private const val foregroundIndex = 2f
    }

    @Composable
    fun Content() = Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val textAlign = when (type) {
            OnboardingPageType.CenterOriented -> TextAlign.Center
            OnboardingPageType.TopOriented -> TextAlign.Start
        }

        Column(
            modifier = Modifier.zIndex(backgroundIndex).fillMaxSize(),
            verticalArrangement = when (type) {
                OnboardingPageType.CenterOriented -> Arrangement.Center
                OnboardingPageType.TopOriented -> Arrangement.Top
            }
        ) {
            Image(
                image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth(),
            )
        }

        Column(
            modifier = Modifier
                .zIndex(foregroundIndex)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(
                Modifier.fillMaxHeight(
                    when (type) {
                        OnboardingPageType.CenterOriented -> 0.64f
                        OnboardingPageType.TopOriented -> 0.07f
                    }
                )
            )

            if (showSkip) Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onSkip
                ) {
                    Text(
                        DevRushTheme.strings.onboardingSkip,
                        textAlign = TextAlign.End,
                        style = DevRushTheme.typography.sfPro14,
                        color = DevRushTheme.colors.c2
                    )
                }
            }

            Column {
                Text(
                    title,
                    textAlign = textAlign,
                    style = DevRushTheme.typography.sfProBold36,
                    color = DevRushTheme.colors.c1,
                    modifier = Modifier.fillMaxWidth()
                )

                Gap(8)

                Text(
                    description,
                    textAlign = textAlign,
                    style = DevRushTheme.typography.sfPro14,
                    color = DevRushTheme.colors.c2,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

