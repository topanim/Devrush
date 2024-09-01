package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourses

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable


@Serializable
data class GetStudentCoursesRequest(
    override val token: String,
    val fields: String = "{id,beginDate,endDate,state,flowBeginDate,accessBeginDate,accessEndDate,isSubscription,course{id,name,dontAskFeedback},currentCourseItem{id},feedback,progress,student{id}}",
) : ApiAuthRequest