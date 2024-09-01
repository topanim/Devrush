package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.file
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonState
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.LessonRequestState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme.colors
import io.eduonline.devrush.devrushlmsmultiplatform.theme.typography
import io.eduonline.devrush.devrushlmsmultiplatform.utils.format
import org.jetbrains.compose.resources.vectorResource

data class LessonFile(
    val name: String,
    val cloudKey: String,
    val size: Long,
    val type: String,
    val extension: String,
    val id: String,
) {
    private val _downloadingState = mutableStateOf<LessonRequestState>(LessonRequestState.Idle)

    var downloadingState: LessonRequestState
        get() = _downloadingState.value
        set(value) {
            _downloadingState.value = value
        }

    @Composable
    fun content(
        onEvent: (CourseLessonEvent) -> Unit,
    ) = Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().height(70.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Image(
                    imageVector = vectorResource(Res.drawable.file),
                    contentDescription = name,
                    alignment = Alignment.Center,
                    modifier = Modifier.align(Alignment.Center)
                )

                Text(extension, Modifier.align(Alignment.Center))
            }

            Gap(8)

            Column {
                Text(
                    name,
                    color = colors.c1,
                    style = typography.sfProBold16
                )

                Gap(4)

                Text(
                    size.format(),
                    color = colors.c2,
                    style = typography.sfPro14
                )
            }
        }

        when (downloadingState) {
            LessonRequestState.Error -> DownloadButton(true, onEvent)
            LessonRequestState.Idle -> DownloadButton(false, onEvent)
            LessonRequestState.Success -> Icon(Icons.Filled.Check, "Downloaded", tint = colors.c1)
            LessonRequestState.Loading -> CircularProgressIndicator(
                color = colors.c1,
                strokeCap = StrokeCap.Round
            )
        }

    }

    @Composable
    private fun DownloadButton(
        retry: Boolean = false,
        onEvent: (CourseLessonEvent) -> Unit,
    ) = IconButton(
        onClick = {
            onEvent(CourseLessonEvent.LoadFile(id, name, extension))
        }
    ) {
        if (retry) Icon(
            Icons.Filled.Refresh,
            contentDescription = "Retry",
            tint = colors.c1
        )
        else Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "Download",
            tint = colors.c1
        )
    }
}