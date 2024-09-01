package io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.courseRepository

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.database.AppDatabase
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.CourseDBO
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.CourseModule

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.GetSchoolContentItemsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.SectionType

class CourseRepository(
    private val api: CourseModule,
    private val database: AppDatabase,
    private val authRepository: AuthTokenRepository,
) {

    suspend fun getAllFromServer(): List<Course> {
        val schoolContentItems = api.getSchoolContentItems(
            GetSchoolContentItemsRequest(
                authRepository.getTokens().accessToken,
                SectionType.Course
            )
        ).checkedRefresh(authRepository).checkedResult().items
        return schoolContentItems.map { it.course!! }
    }

    suspend fun gelAllFromDatabase(): List<Course> {
        val cashedCourses = database.getCourseDao().getAll().map { it.toCourse() }
        return cashedCourses.ifEmpty { emptyList() }
    }

    suspend fun saveCourses(courses: List<Course>) {
        val coursesDBO = courses.map { it.toCourseDbo() }
        database.getCourseDao().insert(coursesDBO)
    }

    suspend fun deleteCourses(courses: List<CourseDBO>) {
        database.getCourseDao().delete(courses)
    }

    suspend fun cleanCourses() {
        database.getCourseDao().clean()
    }
}