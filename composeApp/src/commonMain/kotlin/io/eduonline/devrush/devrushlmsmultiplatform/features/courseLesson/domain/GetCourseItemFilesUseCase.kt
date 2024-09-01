package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.domain

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.CourseModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItemFiles.GetStudentCourseItemFilesRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItemFiles.StudentCourseItemFile
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseV2.GetStudentCourseInfoV2Request
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.ui.components.LessonFile

class GetCourseItemFilesUseCase(
    private val courseModule: CourseModule,
    private val tokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(
        courseId: String,
        lessonId: String,
    ): List<LessonFile> {
        val token = tokenRepository.accessToken()
        val studentCourseV2 = courseModule
            .getStudentCourseV2(
                GetStudentCourseInfoV2Request(token, courseId)
            )
            .checkedRefresh(tokenRepository)
            .checkedResult()
            .items.first()

        return courseModule
            .getStudentCourseItemFiles(
                GetStudentCourseItemFilesRequest(
                    token, studentCourseV2.id, lessonId
                )
            )
            .checkedRefresh(tokenRepository)
            .checkedResult()
            .files.map { it.toLessonFile() }
    }

    private fun StudentCourseItemFile.toLessonFile() =
        LessonFile(
            name = name,
            cloudKey = cloudKey,
            size = size,
            type = type,
            extension = extension,
            id = id
        )
}