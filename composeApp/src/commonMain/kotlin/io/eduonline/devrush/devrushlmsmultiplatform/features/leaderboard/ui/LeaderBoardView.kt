package io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.bronze_icon
import devrush_multiplatform.composeapp.generated.resources.bronze_rating_icon
import devrush_multiplatform.composeapp.generated.resources.gold_icon
import devrush_multiplatform.composeapp.generated.resources.gold_rating_icon
import devrush_multiplatform.composeapp.generated.resources.silver_icon
import devrush_multiplatform.composeapp.generated.resources.silver_rating_icon
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model.LeaderboardEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.model.LeaderboardViewState
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui.views.Colors
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui.views.LeaderBoardItem
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui.views.LeaderBoardItemState
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui.views.TopLeaderItem
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource


@Composable
fun LeaderBoardView(state: LeaderboardViewState, onClick: (LeaderboardEvent) -> Unit) {

    val goldenIcons = listOf(
        vectorResource(Res.drawable.gold_icon),
        vectorResource(Res.drawable.gold_rating_icon)
    )
    val silverIcons = listOf(
        vectorResource(Res.drawable.silver_icon),
        vectorResource(Res.drawable.silver_rating_icon)
    )

    val bronzeIcons = listOf(
        vectorResource(Res.drawable.bronze_icon),
        vectorResource(Res.drawable.bronze_rating_icon)
    )

    val topLeaderItemState = state.leaderList.take(3)
    val otherLeaderItemState = state.leaderList.drop(3)
    val colors = List(otherLeaderItemState.size / 7) { Colors.entries }.flatten()

    fun getFullName(firstName: String, lastName: String?): String {
        return if (lastName == null) firstName
        else "$lastName $firstName"
    }

    val lazyListState = rememberLazyListState()
    val animationActionExpression = lazyListState.firstVisibleItemScrollOffset <= 110
    val animationExpression = animationActionExpression && lazyListState.firstVisibleItemIndex == 0
    val leadersTopAnimationSpector by animateFloatAsState(
        targetValue = if (animationExpression) 1f else 0f
    )

    LaunchedEffect(lazyListState.firstVisibleItemScrollOffset) {
        if (lazyListState.firstVisibleItemIndex == 0 && lazyListState.firstVisibleItemScrollOffset > 0) {
            val scrollToUp = !animationActionExpression
            lazyListState.animateScrollToItem(if (scrollToUp) 1 else 0)
        }
    }

    Column {
        DevRushTopAppBar(title = DevRushTheme.strings.profileLeaderboard) { onClick(LeaderboardEvent.CloseScreenClicked) }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            state = lazyListState
        ) {
            item {
                val scale = animateFloatAsState(
                    targetValue = if (lazyListState.firstVisibleItemIndex == 0 && lazyListState.firstVisibleItemScrollOffset > 0) {
                        1f - lazyListState.firstVisibleItemScrollOffset / 200f
                    } else {
                        1f
                    },
                ).value

                Card(
                    Modifier.fillMaxWidth().height(200.dp),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                    colors = CardDefaults.cardColors(DevRushTheme.colors.background),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(leadersTopAnimationSpector)
                                .graphicsLayer {
                                    scaleX = scale.coerceIn(0.5f, 1f)
                                    scaleY = scale.coerceIn(0.5f, 1f)
                                    alpha = scale.coerceIn(0f, 1f)
                                }
                                .align(Alignment.TopCenter)
                                .padding(
                                    top = 10.dp,
                                    start = 12.dp,
                                    end = 12.dp,
                                    bottom = 20.dp
                                )
                        ) {
                            val boxItemModifier = Modifier.fillMaxHeight().weight(1f)
                            if (topLeaderItemState.size > 1) {
                                Box(boxItemModifier) {
                                    TopLeaderItem(
                                        item = LeaderBoardItemState(
                                            place = topLeaderItemState[1].place.toString(),
                                            name = getFullName(
                                                topLeaderItemState[1].student.firstName ?: "",
                                                topLeaderItemState[1].student.lastName ?: ""
                                            ),
                                            imageCloudKey =
                                            topLeaderItemState[1].student.avatar?.cloudKey ?: "",
                                            points = topLeaderItemState[1].score.toString(),
                                            color = Colors.entries[1].color,
                                            isMe = false
                                        ),
                                        imageList = silverIcons,
                                        modifier = Modifier.align(Alignment.BottomCenter)
                                    )
                                }
                            }
                            Box(boxItemModifier) {
                                TopLeaderItem(
                                    item = LeaderBoardItemState(
                                        place = topLeaderItemState[0].place.toString(),
                                        name = getFullName(
                                            topLeaderItemState[0].student.firstName ?: "",
                                            topLeaderItemState[0].student.lastName ?: ""
                                        ),
                                        imageCloudKey =
                                        topLeaderItemState[0].student.avatar?.cloudKey ?: "",
                                        points = topLeaderItemState[0].score.toString(),
                                        color = Colors.entries[0].color,
                                        isMe = false
                                    ),
                                    imageList = goldenIcons,
                                    modifier = Modifier.align(Alignment.TopCenter)
                                )
                            }
                            if (state.leaderList.size > 2) {
                                Box(boxItemModifier) {
                                    TopLeaderItem(
                                        item = LeaderBoardItemState(
                                            place = topLeaderItemState[2].place.toString(),
                                            name = getFullName(
                                                topLeaderItemState[2].student.firstName ?: "",
                                                topLeaderItemState[2].student.lastName ?: ""
                                            ),
                                            imageCloudKey =
                                            topLeaderItemState[2].student.avatar?.cloudKey ?: "",
                                            points = topLeaderItemState[2].score.toString(),
                                            color = Colors.entries[2].color,
                                            isMe = false
                                        ),
                                        imageList = bronzeIcons,
                                        modifier = Modifier.align(Alignment.BottomCenter)
                                    )
                                }
                            }
                        }
                    }
                }

                Gap(size = 15)
            }

            if (otherLeaderItemState.isNotEmpty()) {
                items(otherLeaderItemState) {
                    val name = getFullName(it.student.firstName ?: "", it.student.lastName ?: "")
                    LeaderBoardItem(
                        LeaderBoardItemState(
                            //state,
                            place = it.place.toString(),
                            name = name,
                            imageCloudKey = it.student.avatar?.cloudKey ?: "",
                            points = it.score.toString(),
                            color = colors[it.place - 1].color,
                            isMe = state.currentUser == it
                        )
                    )
                }
            }
        }
    }
}