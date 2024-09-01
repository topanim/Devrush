package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseInfo

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import kotlinx.serialization.Serializable

@Serializable
data class GetStudentCourseInfoRequest(
    override val token: String,
    val studentCourseId: String,
    val fields: String = "{id,beginDate,endDate,state,flowBeginDate,accessBeginDate,accessEndDate,isSubscription,course{id,cert{id,image{cloudKey,id}},name,shortDescription,dontAskFeedback},currentCourseItem{id},feedback,progress,student{id}}",
) : ApiAuthRequest