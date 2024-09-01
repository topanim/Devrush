package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonState
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.LessonRequestState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme.colors
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme.strings
import io.eduonline.devrush.devrushlmsmultiplatform.theme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilesSheet(
    state: CourseLessonState,
    onEvent: (CourseLessonEvent) -> Unit,
) = ModalBottomSheet(
    containerColor = colors.background,
    onDismissRequest = {
        onEvent(CourseLessonEvent.CloseBottomSheet)
    }
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            strings.courseLessonFilesForLesson,
            color = colors.c1,
            style = typography.sfProBold18
        )

        Gap(16)

        Box(
            Modifier.clip(RoundedCornerShape(12.dp))
                .height(IntrinsicSize.Min)
        ) {
            when (state.filesFetchState) {
                LessonRequestState.Loading -> Text(
                    strings.courseLessonLoading,
                    color = colors.c2,
                    style = typography.sfPro14
                )
                else -> {
                    if (state.files.isEmpty()) Text(
                        strings.courseLessonMissing,
                        color = colors.c2,
                        style = typography.sfPro14
                    )

                    else Column(Modifier.fillMaxWidth()) {
                        state.files.forEach {
                            it.content(onEvent); Gap(8)
                        }
                    }
                }
            }
        }

        Gap(16)
    }
}