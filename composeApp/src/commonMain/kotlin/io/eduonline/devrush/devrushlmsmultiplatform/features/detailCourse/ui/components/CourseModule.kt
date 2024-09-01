package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseProgramModule
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseEvent
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark

@Composable
fun LessonsContainer(available: Boolean, content: @Composable () -> Unit) = Box(
    Modifier.fillMaxWidth().background(
        if (available) if (LocalThemeIsDark.current) DevRushTheme.colors.c6
        else Color.Transparent
        else if (LocalThemeIsDark.current) DevRushTheme.colors.c3
        else DevRushTheme.colors.c5,
        RoundedCornerShape(8.dp)
    ).border(
        BorderStroke(1.dp, DevRushTheme.colors.c5),
        RoundedCornerShape(8.dp)
    )
) { Column(Modifier.fillMaxWidth().padding(16.dp, 10.dp)) { content() } }

@Composable
fun CourseModule(
    courseModule: CourseProgramModule,
    stepByStep: Boolean,
    onEvent: (DetailCourseEvent) -> Unit = {},
) {
    val collapseState = remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize()
        .clip(RoundedCornerShape(8.dp))
        .background(
            if (courseModule.available) if (LocalThemeIsDark.current) DevRushTheme.colors.c6
            else Color.Transparent
            else if (LocalThemeIsDark.current) DevRushTheme.colors.c3
            else DevRushTheme.colors.c5
        ).border(
            BorderStroke(1.dp, DevRushTheme.colors.c5),
            RoundedCornerShape(8.dp)
        ).clickable {
            collapseState.value = !collapseState.value
        }
    ) {
        Column(
            Modifier.padding(16.dp, 10.dp).fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "${DevRushTheme.strings.courseInfoModule} ${courseModule.moduleNumber}",
                    style = DevRushTheme.typography.sfProBold12,
                    color = DevRushTheme.colors.c2
                )

                if (!courseModule.available) {
                    Gap(8)
                    Text(
                        text = DevRushTheme.strings.courseInfoModuleUnavailable,
                        style = DevRushTheme.typography.sfPro12,
                        color = DevRushTheme.colors.c1
                    )
                }

            }

            Gap(8)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = courseModule.title ?: DevRushTheme.strings.courseInfoUntitled,
                    style = DevRushTheme.typography.sfProBold18,
                    color = DevRushTheme.colors.c1
                )

                val rotation = if (collapseState.value) 180f else 0f
                val angle: Float by animateFloatAsState(
                    targetValue = rotation,
                    animationSpec = tween(
                        durationMillis = 250,
                        easing = LinearEasing
                    )
                )

                Icon(
                    Icons.Default.ArrowDropDown,
                    tint = DevRushTheme.colors.c1,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .rotate(angle)
                        .clip(RoundedCornerShape(5.dp))

                )
            }

            if (collapseState.value)
                courseModule.lessons.forEach {
                    CourseModuleLesson(
                        it, stepByStep, Modifier.padding(top = 10.dp),
                    ) { onEvent(DetailCourseEvent.OpenLesson(it.id)) }
                }
        }
    }
}

