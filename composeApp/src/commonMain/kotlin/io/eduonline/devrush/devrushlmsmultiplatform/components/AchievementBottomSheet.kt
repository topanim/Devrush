package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.dark_theme_ruble
import devrush_multiplatform.composeapp.generated.resources.ruble
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementBottomSheet(achievement: Achievement, event: () -> Unit) {
    val animationScale = remember { Animatable(1f) }
    LaunchedEffect(event) {
        animationScale.animateTo(
            1.3f,
            animationSpec = tween(
                durationMillis = 350,
                easing = FastOutSlowInEasing
            )
        )
        animationScale.animateTo(
            0.95f,
            animationSpec = tween(
                durationMillis = 325,
                easing = FastOutSlowInEasing
            )
        )
        animationScale.animateTo(
            1.1f,
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        )
        animationScale.animateTo(
            1f,
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        )
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        modifier = Modifier.wrapContentHeight(unbounded = true),
        sheetState = sheetState,
        containerColor = DevRushTheme.colors.background,
        contentColor = DevRushTheme.colors.background,
        onDismissRequest = {
            event()
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 38.dp, top = 0.dp, end = 38.dp)
            ) {
                Title(
                    text = DevRushTheme.strings.profileAllAchievements,
                    modifier = Modifier
                )//описание
                Gap(size = 16)
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .scale(animationScale.value),
                    model = FileModule.getFullFilePath(achievement.imageCloudKey),
                    contentDescription = null,
                )
                Gap(size = 21)
                Text(
                    text = achievement.title ?: "",
                    style = DevRushTheme.typography.sfProBold30,
                    color = DevRushTheme.colors.c1,
                    modifier = Modifier.align(Alignment.Start)
                )
                Gap(18)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Image(
                        imageVector = if (LocalThemeIsDark.current) vectorResource(Res.drawable.dark_theme_ruble) else vectorResource(
                            Res.drawable.ruble
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Gap(10)
                    Text(
                        modifier = Modifier
                            .height(24.dp),
                        text = "${achievement.points}",
                        color = if (LocalThemeIsDark.current) DevRushTheme.colors.baseBlue2 else DevRushTheme.colors.newGrey,
                        style = DevRushTheme.typography.sfPro20,
                    )
                }
                Gap(16)
                Text(
                    text = achievement?.description ?: "",
                    modifier = Modifier.align(
                        Alignment.Start
                    ),
                    style = DevRushTheme.typography.sfPro20,
                    color = if (LocalThemeIsDark.current) DevRushTheme.colors.baseBlue2 else DevRushTheme.colors.newGrey,
                )
                Gap(20)
            }
        })
}