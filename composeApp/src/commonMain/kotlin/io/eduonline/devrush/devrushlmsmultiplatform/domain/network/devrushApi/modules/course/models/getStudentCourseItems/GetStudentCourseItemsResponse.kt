package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItems

import kotlinx.serialization.Serializable

@Serializable
data class GetStudentCourseItemsResponse(
    val items: List<StudentCourseItem>,
)

@Serializable
data class StudentCourseItem(
    val id: String,
    val parentItemId: String?,
    val title: String?,
    val type: String,
    val position: Int,
    val availability: String,
    val disableTimeLimits: Boolean,
    val regularTimeDelayActive: Boolean,
    val regularTimeDelay: Int?,
    val regularTimeDelayCondition: String?,
    val regularTimeDelayMeasureType: String?,
    val flowTimeDelayActive: Boolean,
    val flowTimeDelay: Int?,
    val flowTimeDelayCondition: String?,
    val flowTimeDelayMeasureType: String?,
    val dateDelayActive: Boolean,
    val dateDelay: String?,
    val preventCopingAndSelectingText: Boolean,
    val blockCourseUntilTasksApproved: Boolean,
    val isOptional: Boolean,
    val allowComments: Boolean,
    val showComments: Boolean,
    val ratingEnabled: Boolean,
    val ratingRequired: Boolean,
    val ratingCommentRequired: Boolean,
    val theoryVisible: Boolean,
    val testsVisible: Boolean,
    val tasksVisible: Boolean,
    val requiredPlans: List<String>?,
    val progress: Progress?,
    val tasks: List<Task>
)

@Serializable
data class Progress(
    val id: String,
    val beginDate: String,
    val completeDate: String?,
    val precompleteDate: String?,
    val status: String,
    val theoryStatus: Boolean?,
    val testStatus: String?,
    val taskStatus: String?,
    val numberUncheckedTasks: Int,
    val numberCorrectTasks: Int,
    val numberIncorrectTasks: Int,
)

@Serializable
data class Task(
    val id: String,
)