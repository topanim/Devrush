package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.ui.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.ic_paper_clip
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonEvent
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme.colors
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.vectorResource

@Composable
fun LessonFooter(
    onEvent: (CourseLessonEvent) -> Unit,
) = Box(
    Modifier.background(colors.background)
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(14.dp, 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    if (LocalThemeIsDark.current) colors.c3
                    else colors.c5
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = LocalIndication.current
                ) { onEvent(CourseLessonEvent.OpenBottomSheet) }
        ) {
            Icon(
                vectorResource(Res.drawable.ic_paper_clip),
                contentDescription = "Open files",
                tint = colors.c1,
                modifier = Modifier
                    .padding(8.dp)
                    .rotate(45f)
                    .align(Alignment.Center)
            )
        }
    }
}