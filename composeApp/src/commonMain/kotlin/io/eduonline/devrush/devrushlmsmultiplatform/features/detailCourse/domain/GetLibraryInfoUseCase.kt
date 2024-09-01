package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain

import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryInfo.GetSchoolLibraryInfoRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryInfo.GetSchoolLibraryInfoResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryItem.GetSchoolLibraryItemRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryItem.SchoolLibraryItem
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseInfo
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseLesson
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.models.CourseProgramModule


class GetLibraryInfoUseCase(
    private val api: io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.CourseModule,
    private val authTokenRepository: AuthTokenRepository,
) {
    init {
        Logger.d("INIT") { "GetLibraryInfoUseCase" }
    }

    suspend operator fun invoke(
        libraryId: String,
    ): CourseInfo {
        val token = authTokenRepository.getTokens().accessToken

        val studentCourse = api.getSchoolLibraryInfo(
            GetSchoolLibraryInfoRequest(token, libraryId)
        ).checkedRefresh(authTokenRepository).checkedResult()

        val studentCourseItems = api.getSchoolLibraryItem(
            GetSchoolLibraryItemRequest(token, libraryId)
        ).checkedRefresh(authTokenRepository).checkedResult()

        return responseDataToLibraryInfo(
            studentCourse,
            studentCourseItems.items
        )

    }

    private fun responseDataToLibraryInfo(
        library: GetSchoolLibraryInfoResponse,
        libraryItems: List<SchoolLibraryItem>,
    ): CourseInfo {
        val studentCourseItemsList = libraryItems.toMutableList()
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
                completed = false,
            )

            if (item.parentItem?.id == null) lessons.add(CourseProgramElement.Lessons(listOf(lesson)))
            else courseItemsTree[item.parentItem.id]?.lessons?.add(lesson)
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
                        completed = true
                        available = true
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
            title = library.title,
            shortDescription = library.shortDescription,
            imageKey = "",
            isAccess = true,
            progress = 100f,
            certificate = false,
            certificateImageCloudKey = null,
            program = groupedList,
            lastLessonId = lastLessonId
        )
    }
}