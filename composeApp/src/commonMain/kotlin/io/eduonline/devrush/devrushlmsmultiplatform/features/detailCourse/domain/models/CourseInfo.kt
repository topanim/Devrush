package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models

import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.CourseProgramElement

data class CourseInfo(
    val title: String,
    val isAccess: Boolean,
    val shortDescription: String,
    val imageKey: String,
    val progress: Float,
    val certificate: Boolean,
    val lastLessonId: String,
    val certificateImageCloudKey: String?,
    val program: List<CourseProgramElement>,
)

