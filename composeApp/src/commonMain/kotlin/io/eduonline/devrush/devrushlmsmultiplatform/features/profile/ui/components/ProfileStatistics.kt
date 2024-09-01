package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.cup
import devrush_multiplatform.composeapp.generated.resources.medal_star
import io.eduonline.devrush.devrushlmsmultiplatform.LocalTheme
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Avatar
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Student
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.vectorResource

@Composable
fun ProfileStatistics(state: ProfileState) {

    val currentStudent = state.profile?.let {
        Student(
            firstName = it.firstName,
            lastName = it.lastName,
            avatar = Avatar(it.avatar?.cloudKey)
        )
    }

    val currentUser = state.leaderList?.find {
        it.student.firstName == state.profile?.firstName &&
                it.student.lastName == state.profile?.lastName &&
                it.student.avatar?.cloudKey == state.profile?.avatar?.cloudKey
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = DevRushTheme.strings.profileMyStatistics,
                style = DevRushTheme.typography.sfProBold18,
                textAlign = TextAlign.Start,
                color = DevRushTheme.colors.c1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                ProfileStatCard(
                    icon = vectorResource(Res.drawable.medal_star),
                    value = currentUser?.score.toString() ?: "N/A",
                    label = DevRushTheme.strings.profilePoints,
                    color = DevRushTheme.colors.basePink1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                ProfileStatCard(
                    icon = vectorResource(Res.drawable.cup),
                    value = currentUser?.place.toString() ?: "N/A",
                    label = DevRushTheme.strings.profileRank,
                    color = DevRushTheme.colors.baseGreen1,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}


@Composable
fun ProfileStatCard(
    icon: ImageVector,
    value: String,
    label: String,
    color: Color,
    modifier: Modifier,
) {
    val currentTheme = LocalTheme.current

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = if (LocalThemeIsDark.current || currentTheme.value == Theme.Dark) DevRushTheme.colors.c4
                else DevRushTheme.colors.c5,
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 14.dp)
//            .width(160.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = Color.Transparent)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = value,
                    color = DevRushTheme.colors.c1,
                    style = DevRushTheme.typography.sfPro14
                )
                Text(
                    text = label,
                    color = DevRushTheme.colors.c2,
                    style = DevRushTheme.typography.sfPro14
                )
            }
        }
    }
}
