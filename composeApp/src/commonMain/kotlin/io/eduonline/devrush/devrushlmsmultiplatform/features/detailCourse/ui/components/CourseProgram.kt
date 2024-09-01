package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.CourseProgramElement
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseInfo
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseEvent


@Composable
fun CourseProgram(
    courseInfo: CourseInfo,
    stepByStep: Boolean,
    onEvent: (DetailCourseEvent) -> Unit = {},
) = Column {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        courseInfo.program.forEach {
            when (it) {
                is CourseProgramElement.Module -> CourseModule(it.module, stepByStep, onEvent)
                is CourseProgramElement.Lessons -> LessonsContainer(it.available) {
                    it.items.forEach { lesson ->
                        CourseModuleLesson(
                            lesson,
                            stepByStep,
                        ) {
                            onEvent(DetailCourseEvent.OpenLesson(lesson.id))
                        }
                    }
                }

            }
        }
    }
}
