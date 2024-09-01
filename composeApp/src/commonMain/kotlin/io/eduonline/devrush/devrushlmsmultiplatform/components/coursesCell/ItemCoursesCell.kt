package io.eduonline.devrush.devrushlmsmultiplatform.components.coursesCell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.ic_medal
import devrush_multiplatform.composeapp.generated.resources.mock_3
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemCoursesCell(course: Course, onClick: () -> Unit) {

    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 20.dp)
        .height(100.dp)
        .clip(RoundedCornerShape(10.dp))
        .clickable { onClick() }
        .background(DevRushTheme.colors.c6)
        .padding(10.dp)
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxHeight().aspectRatio(16f / 9f),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (course.primaryImageCloudKey != null) {
                    AsyncImage(
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
                if (course.studentCourse.progress.toInt() == 100) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_medal),
                        contentDescription = null,
                        tint = DevRushTheme.colors.basePink2,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 5.dp, end = 5.dp)
                            .clip(CircleShape)
                            .background(DevRushTheme.colors.basePurple1)
                            .size(30.dp)
                            .padding(3.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(10.dp))

        Column {
            Text(
                text = course.title?:"",
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