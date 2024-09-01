package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.baseline_info_24
import devrush_multiplatform.composeapp.generated.resources.dark_theme_ruble
import devrush_multiplatform.composeapp.generated.resources.ruble
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.vectorResource

@Composable
fun AchievementCard(
    pew: Achievement,
    activity: Boolean,
    isOpen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .width(105.dp)
            .clickable {
                isOpen()
            }
    ) {
        ElevatedCard(
            elevation = CardDefaults.elevatedCardElevation(6.dp),
        ) {
            Box(
                modifier = Modifier
                    .width(123.dp)
                    .height(80.dp)
                    .shadow(100.dp)
                    .background(Color.Transparent)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize()
                        .background(
                            if (activity) {
                                DevRushTheme.colors.basePurple3
                            } else {
                                Color(0xffF2F2F2)
                            }
                        ),
                    colorFilter = if (!activity) {
                        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
                    } else {
                        null
                    },
                    model = FileModule.getFullFilePath(pew.customCloudKey),
                    contentDescription = null,
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = {
                            isOpen()
                        },
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(
                            imageVector = vectorResource(Res.drawable.baseline_info_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(10.dp)
                        )
                    }
                }
            }
        }

        Gap(6)

        ButtonDefaults.outlinedButtonColors()

        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = pew.title ?: "",
                color = DevRushTheme.colors.c1,
                style = DevRushTheme.typography.sfProBold14,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Gap(8)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = if (LocalThemeIsDark.current) vectorResource(Res.drawable.dark_theme_ruble) else vectorResource(
                        Res.drawable.ruble
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Gap(10)

                Text(
                    modifier = Modifier
                        .height(17.dp),
                    text = "${pew.points}",
                    color = if (LocalThemeIsDark.current) DevRushTheme.colors.baseBlue2 else DevRushTheme.colors.newGrey,
                    style = DevRushTheme.typography.sfPro14,
                )
            }
        }
    }
}