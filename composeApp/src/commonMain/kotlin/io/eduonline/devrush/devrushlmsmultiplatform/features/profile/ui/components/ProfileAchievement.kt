package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ProfileAchievement(
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxWidth()
) {
    Column {
        TitleRow(
            DevRushTheme.strings.profileAchievements,
            onClick = { onEvent.invoke(ProfileEvent.PresentAchievements) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            state.achievementsNoActivity?.let {
                state.achievementsActivity?.plus(state.achievementsNoActivity)
                    ?.let { it1 -> AchievementRow(it1) }
            }
        }
    }
}

@Composable
fun AchievementRow(
    achievement: List<Achievement>,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(achievement.take(20)) { achiev ->
            AchievementItem(
                achiev, Modifier.padding(start = if (achiev == achievement.first()) 20.dp else 0.dp)
                    .padding(end = if (achiev == achievement.last()) 20.dp else 0.dp)
            )
        }
    }
}
