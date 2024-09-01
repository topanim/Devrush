package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ProfileImage(
    state: ProfileState,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp)
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(top = 23.dp)
    ) {
        if (state.profile!!.avatar?.cloudKey == null) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(color = DevRushTheme.colors.baseGreen1)

            ) {
                Text(
                    if (state.profile.firstName.isEmpty()) ""
                    else (state.profile.firstName.firstOrNull()?.uppercase() ?: "") +
                            (state.profile.lastName?.firstOrNull()?.uppercase() ?: ""),
                    style = DevRushTheme.typography.sfProBold24,
                    color = Color.White
                )

            }
        } else {
            Box(modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)) {
                Image(
                    painter = ColorPainter(DevRushTheme.colors.baseGreen1),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                AsyncImage(
                    model = FileModule.getFullFilePath(state.profile.avatar?.cloudKey!!),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
        ) {
            Text(
                text = DevRushTheme.strings.profileName,
                color = DevRushTheme.colors.c2,
                style = DevRushTheme.typography.sfPro14,
            )
            Gap(6)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.dp,
                        color = DevRushTheme.colors.c5,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(color = DevRushTheme.colors.c6)
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "${state.profile.firstName} ${
                        state.profile.lastName?.firstOrNull()?.plus(".") ?: ""
                    }",
                    color = DevRushTheme.colors.c1,
                    style = DevRushTheme.typography.sfPro14,
                    modifier = Modifier.padding(1.dp)
                )
            }
        }

        Gap(8)
    }
}