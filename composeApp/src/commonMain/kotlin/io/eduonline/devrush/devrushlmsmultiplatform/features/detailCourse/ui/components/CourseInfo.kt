package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.ProgressBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseInfo
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseEvent
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

sealed class CourseInfoTab {
    data object Basic : CourseInfoTab()
    data object CourseProgram : CourseInfoTab()

    companion object {
        fun toList(): List<CourseInfoTab> = listOf(Basic, CourseProgram)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseInfo(
    courseInfo: CourseInfo,
    selectedTabIndex: MutableState<Int>,
    stepByStep: Boolean,
    tabs: List<CourseInfoTab>,
    onEvent: (DetailCourseEvent) -> Unit = {},
) = Column {
    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex.value,
        containerColor = Color.Transparent,
        contentColor = DevRushTheme.colors.blue1,
        divider = {},
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                color = DevRushTheme.colors.blue1,
                modifier = Modifier.tabIndicatorOffset(
                    selectedTabIndex.value,
                    matchContentSize = true
                ),
                width = Dp.Unspecified,
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex.value == index,
                onClick = { selectedTabIndex.value = index },
                text = {
                    Text(
                        when (tab) {
                            CourseInfoTab.Basic -> DevRushTheme.strings.courseInfoTabsBasic
                            CourseInfoTab.CourseProgram -> DevRushTheme.strings.courseInfoTabsCourseProgram
                        }
                    )
                },
                selectedContentColor = DevRushTheme.colors.blue1,
                modifier = Modifier.clip(RoundedCornerShape(15.dp))
            )
        }
    }

    Gap(16)

    if (stepByStep) {
        ProgressBar(courseInfo.progress)
        Gap(16)
    }

    when (tabs[selectedTabIndex.value]) {
        CourseInfoTab.Basic -> {
            Text(
                DevRushTheme.strings.profileDescription,
                color = DevRushTheme.colors.c1,
                style = DevRushTheme.typography.sfProBold16
            )
            Gap(8)
            Text(courseInfo.shortDescription, color = DevRushTheme.colors.c1)
        }

        CourseInfoTab.CourseProgram -> CourseProgram(courseInfo, stepByStep, onEvent)
    }
}

