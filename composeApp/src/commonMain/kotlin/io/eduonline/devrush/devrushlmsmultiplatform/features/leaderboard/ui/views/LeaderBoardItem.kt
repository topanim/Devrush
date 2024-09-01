package io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark

data class LeaderBoardItemState(
    val id: String? = null,
    val place: String,
    val name: String,
    val imageCloudKey: String,
    val points: String,
    val color: Color,
    var isMe: Boolean,
)

@Composable
fun LeaderBoardItem(state: LeaderBoardItemState) {
    val backgroundColor = when {
        (state.isMe) -> DevRushTheme.colors.isMeColor
        else ->
            if (LocalThemeIsDark.current) DevRushTheme.colors.c4
            else DevRushTheme.colors.c6
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(
                backgroundColor,
                RoundedCornerShape(10.dp)
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(
                    backgroundColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.width(48.dp)) {
                if (state.imageCloudKey != "") {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .align(Alignment.TopEnd)
                            .clip(CircleShape)
                    ) {
                        Image(
                            painter = ColorPainter(DevRushTheme.colors.baseGreen1),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                        AsyncImage(
                            model = FileModule.getFullFilePath(state.imageCloudKey),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                } else {
                    CustomImage(
                        name = state.name,
                        textStyle = DevRushTheme.typography.sfProBold12,
                        color = state.color,
                        modifier = Modifier.size(38.dp).align(Alignment.TopEnd)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .background(
                            if (state.isMe) DevRushTheme.colors.baseWhite else Color(0xFF98ACD5),
                            shape = CircleShape
                        )
                        .align(Alignment.TopStart),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.place,
                        color = Color(0xFF07338C),
                        style = DevRushTheme.typography.sfProBold14
                    )
                }
            }
            Gap(size = 6)

            Text(
                modifier = Modifier.weight(1f),
                text = state.name,
                color = DevRushTheme.colors.c1,
                style = DevRushTheme.typography.sfPro14
            )
            Text(
                modifier = Modifier
                    .background(
                        if (state.isMe) DevRushTheme.colors.baseWhite else Color(0xFF8CB3FF),
                        shape = CircleShape
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                text = state.points,
                color = if (state.isMe) Color(0xFF0E0E0E) else Color(0xFF07338C),
                style = DevRushTheme.typography.sfProBold14
            )
        }
    }
    if (state.isMe) !state.isMe
}