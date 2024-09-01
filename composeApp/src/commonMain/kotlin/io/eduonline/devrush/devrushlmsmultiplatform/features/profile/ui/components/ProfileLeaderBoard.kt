package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.bronze_icon
import devrush_multiplatform.composeapp.generated.resources.gold_icon
import devrush_multiplatform.composeapp.generated.resources.silver_icon
import io.eduonline.devrush.devrushlmsmultiplatform.LocalTheme
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.vectorResource

@Composable
fun ProfileLeaderBoard(state: ProfileState, onEvent: (ProfileEvent) -> Unit) {

    val currentUser = state.leaderList?.find {
        it.student.firstName == state.profile?.firstName &&
                it.student.lastName == state.profile?.lastName &&
                it.student.avatar?.cloudKey == state.profile?.avatar?.cloudKey
    }
    val leaders = state.leaderList ?: emptyList()
    val currentTheme = LocalTheme.current
    val color =
        if (LocalThemeIsDark.current || currentTheme.value == Theme.Dark) DevRushTheme.colors.c4
        else DevRushTheme.colors.c5

    val vectorList = listOf(
        Res.drawable.gold_icon,
        Res.drawable.silver_icon,
        Res.drawable.bronze_icon
    )

    val profilePlaceList = listOf(
        DevRushTheme.strings.profileFirstPlace,
        DevRushTheme.strings.profileSecondPlace,
        DevRushTheme.strings.profileThirdPlace
    )

    val colorLogo = listOf(
        DevRushTheme.colors.red1,
        DevRushTheme.colors.baseGreen1,
        DevRushTheme.colors.basePink1,
    )

    TitleRow(
        title = DevRushTheme.strings.profileLeaderboard,
        onClick = { onEvent.invoke(ProfileEvent.PresentLeaderBoard) }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = color,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(Color.Transparent)
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
        ) {

            leaders.forEachIndexed { index, leader ->
                if (index > 2) return@forEachIndexed

                val fullName =  if (leader.student.lastName == null) leader.student.firstName ?: ""
                else "${leader.student.lastName} ${leader.student.firstName}"

                ProfileLeaderBoardCard(
                    leader == currentUser,
                    vectorResource(vectorList[index]),
                    profilePlaceList[index],
                    fullName,
                    leader.score.toString(),
                    leader.student.avatar?.cloudKey,
                    colorLogo[index]
                )
                if (index != leaders.size - 1) HorizontalDivider(color = color)
            }
        }
    }
}

@Composable
fun ProfileLeaderBoardCard(
    isInLeaderList: Boolean,
    icon: ImageVector,
    place: String,
    name: String,
    balance: String,
    model: String?,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
            )
            Gap(10)
            Text(
                text = place,
                color = DevRushTheme.colors.c1,
                style = if (isInLeaderList) {
                    DevRushTheme.typography.sfProBold16
                } else {
                    DevRushTheme.typography.sfPro16
                },
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    color = DevRushTheme.colors.c1,
                    style = DevRushTheme.typography.sfPro14
                )
                Text(
                    text = balance,
                    color = DevRushTheme.colors.c2,
                    style = DevRushTheme.typography.sfPro14,
                    modifier = Modifier.align(Alignment.End),
                )
            }

            Gap(8)

            if (model != null) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter = ColorPainter(DevRushTheme.colors.baseGreen1),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                    AsyncImage(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape),
                        model = FileModule.getFullFilePath(model),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            } else {
                CustomImage(
                    name = name,
                    textStyle = DevRushTheme.typography.sfProBold12,
                    color = color,
                    modifier = Modifier.size(38.dp)
                )
            }
        }
    }
}
