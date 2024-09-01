package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItems

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable


@Serializable
data class GetStudentCourseItemsRequest(
    override val token: String,
    val studentCourseId: String,
    val fields: String = "{id,parentItemId,title,type,position,availability,disableTimeLimits,regularTimeDelayActive,regularTimeDelay,regularTimeDelayCondition,regularTimeDelayMeasureType,flowTimeDelayActive,flowTimeDelay,flowTimeDelayCondition,flowTimeDelayMeasureType,dateDelayActive,dateDelay,preventCopingAndSelectingText,blockCourseUntilTasksApproved,isOptional,allowComments,showComments,ratingEnabled,ratingRequired,ratingCommentRequired,theoryVisible,testsVisible,tasksVisible,requiredPlans,progress{id,beginDate,completeDate,precompleteDate,status,theoryStatus,testStatus,taskStatus,numberUncheckedTasks,numberCorrectTasks,numberIncorrectTasks,},tasks{id,}}",
) : ApiAuthRequest
