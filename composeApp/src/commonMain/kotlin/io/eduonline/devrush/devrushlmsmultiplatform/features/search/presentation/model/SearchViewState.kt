package io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.PageType

data class SearchViewState(
    val searchQuery: String = "",
    val courses: List<Course> = emptyList(),
    val libraries: List<Library> = emptyList(),
    val coursesResults: List<Course> = emptyList(),
    val librariesResults: List<Library> = emptyList(),
    val pageType: PageType = PageType.NON,
)