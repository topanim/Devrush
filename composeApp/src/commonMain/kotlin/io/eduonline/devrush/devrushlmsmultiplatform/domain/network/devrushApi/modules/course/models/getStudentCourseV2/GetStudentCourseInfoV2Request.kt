package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseV2

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest

data class GetStudentCourseInfoV2Request(
    override val token: String,

    val studentCourseId: String,
    val fields: String = "{id,beginDate,endDate,state,flowBeginDate,accessBeginDate,accessEndDate,isSubscription,course{id,name,shortDescription,freeAccess,dontAskFeedback,primaryImage{id,file{id,type,cloudKey,extension,size}}},currentCourseItem{id},feedback,progress,student{id}}"
) : ApiAuthRequest