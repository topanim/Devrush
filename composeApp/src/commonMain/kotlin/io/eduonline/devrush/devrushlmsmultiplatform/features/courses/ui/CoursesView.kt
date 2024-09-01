package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.SearchRow
import io.eduonline.devrush.devrushlmsmultiplatform.components.Title
import io.eduonline.devrush.devrushlmsmultiplatform.components.coursesCell.ItemCoursesCell
import io.eduonline.devrush.devrushlmsmultiplatform.components.filterRow.FilterRow
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models.CoursesEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.models.CoursesViewState
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui.views.ItemStartedCoursesCell
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun CoursesView(viewState: CoursesViewState, eventHandler: (CoursesEvent) -> Unit) {
    LazyColumn {
        item {
            Title(
                modifier = Modifier.padding(start = 20.dp),
                DevRushTheme.strings.coursesStartedCourses
            )
            Spacer(modifier = Modifier.size(16.dp))
        }

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(viewState.startedCourses) { course ->
                    ItemStartedCoursesCell(course, Modifier
                        .padding(start = if (course == viewState.startedCourses.first()) 12.dp else 0.dp)
                        .padding(end = if (course == viewState.startedCourses.last()) 20.dp else 0.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            eventHandler.invoke(
                                CoursesEvent.ClickItemCourse(
                                    studentCourseId = course.id,
                                    courseItemId = course.studentCourse.currentCourseItem.id,
                                    imageCloudKey = course.primaryImageCloudKey ?: ""
                                )
                            )
                        }
                        .padding(8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
        }


        item {
            Title(
                modifier = Modifier.padding(start = 20.dp),
                DevRushTheme.strings.coursesListCourses
            )
            Spacer(modifier = Modifier.size(16.dp))
        }

        item {
            FilterRow(viewState.filters) {
                eventHandler.invoke(CoursesEvent.ClickItemFilter(it))
            }
            Spacer(modifier = Modifier.size(16.dp))
        }


        item {
            SearchRow { eventHandler.invoke(CoursesEvent.ClickSearchRow(viewState.allCourses)) }
            Spacer(modifier = Modifier.size(16.dp))
        }
        items(viewState.courses) {
            ItemCoursesCell(it) {
                eventHandler.invoke(
                    CoursesEvent.ClickItemCourse(
                        studentCourseId = it.id,
                        courseItemId = it.studentCourse.currentCourseItem.id,
                        imageCloudKey = it.primaryImageCloudKey ?: ""
                    )
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
    Spacer(modifier = Modifier.size(10.dp))
}