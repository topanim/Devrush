package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseInfo.GetStudentCourseInfoRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseInfo.GetStudentCourseInfoResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItems.GetStudentCourseItemsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItems.StudentCourseItem
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseV2.GetStudentCourseInfoV2Request
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseInfo
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseLesson
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseProgramModule

sealed interface CourseProgramElement {
    data class Lessons(
        val items: List<CourseLesson>,
        var available: Boolean = false,
        var completed: Boolean = false,
    ) : CourseProgramElement {
        operator fun plus(lessons: Lessons): CourseProgramElement {
            return Lessons(this.items + lessons.items)
        }
    }

    data class Module(val module: CourseProgramModule) : CourseProgramElement
}

class GetCourseInfoUseCase(
    private val api: io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.CourseModule,
    private val authTokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(
        studentCourseId: String,
    ): CourseInfo {
        val token = authTokenRepository.getTokens().accessToken

        val studentCourseV2 = api.getStudentCourseV2(
            GetStudentCourseInfoV2Request(token, studentCourseId)
        ).checkedRefresh(authTokenRepository).checkedResult().items.first()

        val studentCourseV1 = api.getStudentCourse(
            GetStudentCourseInfoRequest(token, studentCourseV2.currentCourseItem.id)
        ).checkedRefresh(authTokenRepository).checkedResult()

        val studentCourseItems = api.getStudentCourseItems(
            GetStudentCourseItemsRequest(token, studentCourseV1.studentCourse.id)
        ).checkedRefresh(authTokenRepository).checkedResult()

        return responseDataToCourseInfo(
            studentCourseV1,
            studentCourseItems.items
        )

    }

    private fun responseDataToCourseInfo(
        course: GetStudentCourseInfoResponse,
        courseItems: List<StudentCourseItem>,
    ): CourseInfo {
        val studentCourseItemsList = courseItems.toMutableList()
        val courseItemsTree = mutableMapOf<String, CourseProgramModule>()
        // Этап 1. Формирование дерева курсов

        studentCourseItemsList.forEach { item ->
            if (item.type == "section") courseItemsTree[item.id] = CourseProgramModule(
                position = item.position,
                title = item.title,
                completed = false,
                lessons = mutableListOf()
            )
        }

        // Этап 2. Распределение уроков
        val lessons = mutableListOf<CourseProgramElement.Lessons>()
        studentCourseItemsList.forEach { item ->
            if (item.type != "step") return@forEach

            val lessonStarted = item.progress?.equals(null)?.not() ?: false

            val lesson = CourseLesson(
                id = item.id,
                position = item.position,
                name = item.title,
                started = lessonStarted,
                completed = item.progress?.completeDate?.isNotEmpty() ?: false,
            )

            if (item.parentItemId == null) lessons.add(CourseProgramElement.Lessons(listOf(lesson)))
            else courseItemsTree[item.parentItemId]?.lessons?.add(lesson)
        }

        // Этап 4. Объединение элементов и их сортировка
//        if (lessonStarted) lastLessonId = item.id TODO

        val elements = mutableListOf<CourseProgramElement>()

        lessons.forEach { element ->
            elements.add(element)
        }

        courseItemsTree.values.forEach { element ->
            elements.add(CourseProgramElement.Module(element))
        }

        elements.apply {
            sortBy {
                when (it) {
                    is CourseProgramElement.Lessons -> it.items.first().position
                    is CourseProgramElement.Module -> it.module.position
                }
            }

            var modulesCount = 0
            forEach {
                if (it is CourseProgramElement.Module)
                    it.module.moduleNumber = ++modulesCount
            }
        }

        // Этап 5. Группировка уроков, сортировка уроков в модулях
        val groupedList = mutableListOf<CourseProgramElement>()
        var lastLessonId = ""
        elements.forEach { element ->
            val lastElement = groupedList.lastOrNull()
            if (element is CourseProgramElement.Lessons && lastElement is CourseProgramElement.Lessons) {
                groupedList.removeLast()
                val lastLesson = lastElement.items.last()
                if (lastLesson.started) lastLessonId = lastLesson.id
                groupedList.add(lastElement + element)
            } else element.apply {
                when (this) {
                    is CourseProgramElement.Module -> this.module.apply {
                        this.lessons.sortBy { it.position }
                        this.lessons.findLast { it.started }?.let { lastLessonId = it.id }
                        this.lessons.let {
                            completed = it.lastOrNull()?.completed == true
                            available = it.firstOrNull()?.started == true
                        }
                    }

                    is CourseProgramElement.Lessons -> {
                        val lastLesson = this.items.last()
                        if (lastLesson.started) lastLessonId = lastLesson.id
                    }
                }
                groupedList.add(this)
            }
        }

        groupedList.forEach {
            if (it is CourseProgramElement.Lessons) it.apply {
                completed = it.items.lastOrNull()?.completed == true
                available = it.items.firstOrNull()?.started == true
            }
        }

        return CourseInfo(
            title = course.studentCourse.course.name,
            shortDescription = course.studentCourse.course.shortDescription,
            imageKey = "",
            isAccess = true,
            progress = course.studentCourse.progress,
            certificate = course.studentCourse.course.cert != null,
            certificateImageCloudKey = course.studentCourse.course.cert?.image?.cloudKey,
            program = groupedList,
            lastLessonId = lastLessonId
        )
    }
}