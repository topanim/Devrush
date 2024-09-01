package io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.eduonline.devrush.devrushlmsmultiplatform.components.SearchRow
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model.LibraryEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.model.LibraryViewState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun LibraryView(viewState: LibraryViewState, onEvent: (LibraryEvent) -> Unit) {

    Column {
        SearchRow { onEvent(LibraryEvent.ClickSearchRow(viewState.allLibrary)) }
        Gap(18)

        LazyColumn {
            items(viewState.allLibrary) {
                ItemLibrariesCell(
                    it, Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(93.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(DevRushTheme.colors.c6)
                        .clickable {
                            onEvent.invoke(
                                LibraryEvent.ClickItemLibrary(
                                    libraryId = it.id,
                                    imageCloudKey = it.primaryImageCloudKey
                                )
                            )
                        }.padding(10.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun ItemLibrariesCell(course: Library, modifier: Modifier) {

    Row(modifier) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(16f / 9f),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
        ) {
            AsyncImage(
                model = FileModule.getFullFilePath(course.primaryImageCloudKey ?: ""),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Column {
            Text(
                text = course.title ?: "",
                style = DevRushTheme.typography.sfProBold14,
                maxLines = 2,
                color = DevRushTheme.colors.c1
            )

            Spacer(modifier = Modifier.size(4.dp))

            Text(
                text = course.shortDescription,
                style = DevRushTheme.typography.sfPro12,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = DevRushTheme.colors.c2
            )
        }
    }
}