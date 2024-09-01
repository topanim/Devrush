package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.ic_completed
import devrush_multiplatform.composeapp.generated.resources.ic_start
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseLesson
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CourseModuleLesson(
    lesson: CourseLesson,
    stepByStep: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .clickable(enabled = if (stepByStep) lesson.started else true, onClick = onClick)
) {
    val textStyle: TextStyle
    val textColor: Color
    val icon: ImageVector
    val iconColor: Color

    if (lesson.completed) {
        textStyle = DevRushTheme.typography.sfProBold12
        textColor = DevRushTheme.colors.c2
        iconColor = DevRushTheme.colors.blue1
        icon = vectorResource(Res.drawable.ic_completed)
    } else if (lesson.started) {
        textStyle = DevRushTheme.typography.sfPro12
        textColor = DevRushTheme.colors.blue1
        iconColor = DevRushTheme.colors.blue1
        icon = vectorResource(Res.drawable.ic_start)
    } else {
        textStyle = DevRushTheme.typography.sfPro12
        textColor = DevRushTheme.colors.c2
        iconColor = DevRushTheme.colors.c2
        icon = vectorResource(Res.drawable.ic_start)
    }

    Icon(
        icon,
        tint = iconColor,
        contentDescription = null,
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(5.dp))
    )

    Text(
        lesson.name ?: DevRushTheme.strings.courseInfoUntitled,
        style = textStyle,
        color = textColor
    )
}