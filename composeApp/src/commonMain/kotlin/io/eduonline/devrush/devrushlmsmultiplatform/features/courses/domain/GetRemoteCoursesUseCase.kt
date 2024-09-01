package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.courseRepository.CourseRepository

class GetRemoteCoursesUseCase(
    private val courseRepository: CourseRepository,
) {
    suspend operator fun invoke(): List<Course> {
        val cashed = courseRepository.gelAllFromDatabase()
        val remote = courseRepository.getAllFromServer()
        return if (cashed.isNotEmpty() && cashed == remote) cashed
        else {
            courseRepository.saveCourses(remote)
            remote
        }
    }
}