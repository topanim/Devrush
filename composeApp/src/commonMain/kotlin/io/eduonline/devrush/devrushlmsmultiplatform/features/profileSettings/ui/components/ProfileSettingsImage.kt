package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ProfileSettingsImage(
    state: ProfileSettingsState,
    onEvent: (ProfileSettingsEvent) -> Unit = {},
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxWidth()
) {
    val imageModifier = Modifier
        .size(90.dp)
        .clip(CircleShape)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = imageModifier
                .background(color = DevRushTheme.colors.baseGreen1)

        ) {
            if (state.isPhotoLoading) CircularProgressIndicator(color = DevRushTheme.colors.baseWhite)
            else {
                if (state.fields!!.imageCloudKey == null) {
                    Text(
                        (state.profile!!.firstName.firstOrNull()?.uppercase() ?: "") +
                                (state.profile.lastName?.firstOrNull()?.uppercase() ?: ""),
                        style = DevRushTheme.typography.sfProBold24,
                        color = Color.White
                    )

                } else {
                    AsyncImage(
                        model = FileModule.getFullFilePath(state.fields.imageCloudKey!!),
                        placeholder = ColorPainter(DevRushTheme.colors.baseGreen1),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
            }
        }

        Gap(8)

        TextButton(
            onClick = { onEvent(ProfileSettingsEvent.OpenChoosePhotoView) },
        ) {
            Text(
                DevRushTheme.strings.profileChangePhoto,
                style = DevRushTheme.typography.sfPro14,
                color = DevRushTheme.colors.blue2
            )
        }
    }
}