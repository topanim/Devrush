package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ProfileAccessesAndPayments(state: ProfileState, onEvent: (ProfileEvent) -> Unit) {
    TitleRow(
        title = DevRushTheme.strings.profileAccessPayments,
        onClick = { onEvent.invoke(ProfileEvent.PresentSubscriptions) }
    )
    Spacer(modifier = Modifier.height(16.dp))

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(state.subscriptionsState.take(20)) {
            Column(
                modifier = Modifier.width(200.dp)
                    .padding(start = if (it == state.subscriptionsState.first()) 20.dp else 0.dp)
                    .padding(end = if (it == state.subscriptionsState.last()) 20.dp else 0.dp),
            ) {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.elevatedCardElevation(4.dp),
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = FileModule.getFullFilePath(it.imageCloudKey ?: ""),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = it.title,
                    color = DevRushTheme.colors.c1,
                    style = DevRushTheme.typography.sfProBold14,
                    modifier = Modifier.align(Alignment.CenterHorizontally).height(34.dp)
                )
            }
        }
    }
}
