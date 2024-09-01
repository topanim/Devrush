package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getLicenses

import kotlinx.serialization.Serializable

@Serializable
data class GetLicensesResponse(
    val items: List<License>,
)

@Serializable
data class License(
    val id: String,
    val beginDate: String,
    val endDate: String?,
    val type: String,
    val coursePlan: CoursePlan?,
    val course: Course?,
    val libraryAccess: LibraryAccess?,
)

@Serializable
data class CoursePlan(
    val id: String,
    val name: String,
    val softDeleted: Boolean,
//    val course: Any?
)

@Serializable
data class LibraryAccess(
    val library: LibraryAccessLibrary,
)

@Serializable
data class LibraryAccessLibrary(
    val id: String,
    val name: String,
    val freeAccess: Boolean? = null,
    val softDeleted: Boolean,
    val primaryImage: PrimaryImage?,
)


@Serializable
data class Course(
    val id: String,
    val name: String,
    val softDeleted: Boolean,
    val primaryImage: PrimaryImage?
)

@Serializable
data class PrimaryImage(
    val file: CourseImageFile,
)

@Serializable
data class CourseImageFile(
    val cloudKey: String,
    val extension: String,
    val id: String,
    val name: String? = null,
    val size: String,
    val type: String,
)