package io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark

@Composable
fun TopLeaderItem(
    item: LeaderBoardItemState,
    imageList: List<ImageVector>,
    modifier: Modifier,
) = Box(
    modifier = modifier.height(125.dp)
) {
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .height(44.dp)
            .width(48.dp)
            .align(Alignment.BottomCenter),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            imageVector = imageList.last(),
            contentDescription = null,

            )
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(4.dp),
            text = item.points,
            style = DevRushTheme.typography.sfPro16,
            color = DevRushTheme.colors.baseWhite
        )
    }

    Box(
        modifier = Modifier.size(84.dp).align(Alignment.TopCenter),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(85.dp)
                .offset(y = 7.dp)
                .blur(radius = 7.74.dp)
                .padding(4.dp),
            imageVector = imageList.first(),
            contentDescription = null,
        )
        Image(
            modifier = Modifier.fillMaxSize()
                .offset(y = (-1).dp, x = (-0.5).dp),
            imageVector = imageList.first(),
            contentDescription = null,
        )

        if (item.imageCloudKey != "") {
            Box(
                modifier = Modifier
//                    .size(60.dp)
                    .fillMaxSize()
                    .padding(10.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = ColorPainter(DevRushTheme.colors.baseGreen1),
                    contentDescription = null, // заполните это по необходимости
                    contentScale = ContentScale.FillBounds
                )
                AsyncImage(
                    model = FileModule.getFullFilePath(item.imageCloudKey),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        } else {
            CustomImage(
                name = item.name,
                textStyle = DevRushTheme.typography.sfProBold20,
                color = item.color,
                modifier = Modifier
//                    .size(60.dp)
                    .fillMaxSize()
                    .padding(12.dp)
            )
        }
    }

    Text(
        text = item.name,
        style = DevRushTheme.typography.sfPro14,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Clip,
        color = if (LocalThemeIsDark.current) DevRushTheme.colors.baseWhite
        else DevRushTheme.colors.newDark,
        modifier = Modifier
            .padding(top = 10.dp)
            .align(Alignment.BottomCenter)
    )

}