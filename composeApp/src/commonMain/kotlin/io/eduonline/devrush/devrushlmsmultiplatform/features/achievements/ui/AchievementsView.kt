package io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.AchievementBottomSheet
import io.eduonline.devrush.devrushlmsmultiplatform.components.AchievementCard
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models.AchievementsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models.AchievementsViewState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun AchievementsView(
    viewState: AchievementsViewState,
    onEvent: (AchievementsEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                WindowInsets
                    .systemBars
                    .only(WindowInsetsSides.Vertical)
                    .asPaddingValues()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DevRushTopAppBar(
            title = DevRushTheme.strings.profileAllAchievements,//Все достижения
            onBackClick = { onEvent.invoke(AchievementsEvent.OnBackPress) }
        )
        HorizontalDivider(Modifier.fillMaxWidth(), color = DevRushTheme.colors.c5)


        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            if (!viewState.achievementsActivity.isNullOrEmpty()) {

                item(span = { GridItemSpan(3) }) {
                    Column{
                        Gap(18)
                        Text(
                            text = DevRushTheme.strings.profileActiveAchievements,//"Активные достижения"
                            modifier = Modifier
                                .align(alignment = Alignment.Start),
                            style = DevRushTheme.typography.sfProBold18,
                            color = DevRushTheme.colors.c1
                        )
                    }
                }
                items(viewState.achievementsActivity) {
                    Column{
                        AchievementCard(it, true) {
                            onEvent.invoke(AchievementsEvent.OpenAchievement())
                            onEvent.invoke(AchievementsEvent.SetAchievement(it))
                        }
                    }
                }
            }

            if (!viewState.achievementsNoActivity.isNullOrEmpty()) {
                item(span = { GridItemSpan(3) }) {
                    Column{
                        Text(
                            text = DevRushTheme.strings.profileInactiveAchievements,//Не активные достижения
                            modifier = Modifier
                                .align(alignment = Alignment.Start),
                            style = DevRushTheme.typography.sfProBold18,
                            color = DevRushTheme.colors.c1
                        )
                    }
                }
                items(viewState.achievementsNoActivity) { achievement ->
                    Column  {
                        AchievementCard(achievement, false) {
                            onEvent.invoke(AchievementsEvent.OpenAchievement())
                            onEvent.invoke(AchievementsEvent.SetAchievement(achievement))
                        }
                    }
                }
            }

            item { GridItemSpan(3) }
        }
    }

    if (viewState.openAchievement && viewState.currentAchievement != null) {
        AchievementBottomSheet(viewState.currentAchievement) {
            onEvent.invoke(AchievementsEvent.OpenAchievement())
        }
    }
}