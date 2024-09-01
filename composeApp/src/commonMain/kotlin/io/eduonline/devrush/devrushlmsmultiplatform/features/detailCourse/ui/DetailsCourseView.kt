package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.chat_icon
import devrush_multiplatform.composeapp.generated.resources.empty_star
import devrush_multiplatform.composeapp.generated.resources.filled_star
import devrush_multiplatform.composeapp.generated.resources.ic_medal
import io.eduonline.devrush.devrushlmsmultiplatform.components.RatingBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui.components.CourseBadge
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui.components.CourseInfo
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui.components.CourseInfoTab
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun DetailsCourseView(
    state: DetailCourseLoadingState.Success,
    stepByStep: Boolean,
    onEvent: (DetailCourseEvent) -> Unit = {},
) = Box(
    Modifier.fillMaxSize()
) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val tabs = CourseInfoTab.toList()

    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = FileModule.getFullFilePath(state.course.imageKey),
            contentDescription = "Course banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(230.dp).fillMaxWidth()
        )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = state.course.title,
                style = DevRushTheme.typography.sfProBold24,
                color = DevRushTheme.colors.c1
            )

            Gap(8)

            RatingBar(
                emptyIcon = vectorResource(Res.drawable.empty_star),
                filledIcon = vectorResource(Res.drawable.filled_star),
                stars = 5,
                rating = 4.0,
                iconSize = 16.dp,
                starsColor = DevRushTheme.colors.basePink1
            )

            Gap(8)

            CourseBadge(
                DevRushTheme.strings.courseInfoReviews,
                vectorResource(Res.drawable.chat_icon),
                onClick = {}
            )

            if (stepByStep && state.course.certificate) {
                CourseBadge(
                    DevRushTheme.strings.courseInfoCertificate,
                    vectorResource(Res.drawable.ic_medal),
                    clickable = false
                ) { onEvent(DetailCourseEvent.OpenCourseCertificate) }
            }
            Gap(16)

            CourseInfo(state.course, selectedTabIndex, stepByStep, tabs, onEvent)
        }

        if (tabs[selectedTabIndex.value] !is CourseInfoTab.CourseProgram) Gap(50)
    }

    if (tabs[selectedTabIndex.value] !is CourseInfoTab.CourseProgram) Column(
        Modifier.align(Alignment.BottomCenter)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = DevRushTheme.colors.blue1,
                contentColor = DevRushTheme.colors.c6
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = {
                when (stepByStep) {
                    true -> onEvent(DetailCourseEvent.OpenLesson(state.course.lastLessonId))
                    false -> selectedTabIndex.value = 1
                }
            }
        ) {
            Text(DevRushTheme.strings.courseInfoActionResume)
        }

        Gap(8)
    }
}