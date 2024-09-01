package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryInfo

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest

class GetSchoolLibraryInfoRequest(
    override val token: String,

    val libraryId: String,
    val fields: String = "{id,title,shortDescription,flowBeginDate}",
) : ApiAuthRequest