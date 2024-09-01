package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItemFiles

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest

data class GetStudentCourseItemFilesRequest(
    override val token: String,

    val studentCourseId: String,
    val studentCourseItemId: String
) : ApiAuthRequest