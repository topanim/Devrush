package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.courseRepository.CourseRepository

class GetCachedCoursesUseCase(
    private val courseRepository: CourseRepository,
) {
    suspend operator fun invoke(): List<Course> {
        return courseRepository.gelAllFromDatabase()
    }
}