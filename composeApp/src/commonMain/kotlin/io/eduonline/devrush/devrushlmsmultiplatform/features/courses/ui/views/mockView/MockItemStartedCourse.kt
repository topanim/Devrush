package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui.views.mockView


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.shimmerEffect
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun MockItemStartedCourses(modifier: Modifier) {
    Column(
        modifier = modifier.width(200.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f).clip(
                RoundedCornerShape(10.dp)
            ).shimmerEffect()
        )

        LinearProgressIndicator(
            modifier = Modifier.padding(vertical = 6.dp).height(6.dp),
//            progress = { course.studentCourse.progress / 100f },
            color = DevRushTheme.colors.blue1,
            trackColor = DevRushTheme.colors.c5,
            strokeCap = StrokeCap.Round
        )
        Row(
            modifier = Modifier.fillMaxWidth().height(14.dp).clip(RoundedCornerShape(4.dp))
                .shimmerEffect()
        ) {}
        Row(
            modifier = Modifier.fillMaxWidth(0.4f).height(14.dp).clip(RoundedCornerShape(4.dp))
                .shimmerEffect()
        ) {}
    }
}