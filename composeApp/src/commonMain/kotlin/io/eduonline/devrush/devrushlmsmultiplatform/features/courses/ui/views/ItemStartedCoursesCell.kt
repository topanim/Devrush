package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.mock_3
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemStartedCoursesCell(course: Course, modifier: Modifier) {
    Column(
        modifier = modifier.width(200.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        ElevatedCard(
            modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
        ) {
            if (course.primaryImageCloudKey != null) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = FileModule.getFullFilePath(course.primaryImageCloudKey),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    painter = painterResource(Res.drawable.mock_3),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

        }

        LinearProgressIndicator(
            modifier = Modifier.padding(vertical = 6.dp).height(6.dp),
            progress = { course.studentCourse.progress / 100f },
            color = DevRushTheme.colors.blue1,
            trackColor = DevRushTheme.colors.c5,
            strokeCap = StrokeCap.Round
        )

        Text(
            text = course.title?:"",
            style = DevRushTheme.typography.sfProBold14,
            minLines = 2,
            maxLines = 2,
            color = DevRushTheme.colors.c1
        )
    }
}