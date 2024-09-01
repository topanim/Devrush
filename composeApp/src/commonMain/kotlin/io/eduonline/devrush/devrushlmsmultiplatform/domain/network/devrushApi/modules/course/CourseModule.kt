package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course

import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.HEADER_REFERER
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.authHeader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.GetSchoolContentItemsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.GetSchoolContentItemsResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryInfo.GetSchoolLibraryInfoRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryInfo.GetSchoolLibraryInfoResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryItem.GetSchoolLibraryItemRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolLibraryItem.GetSchoolLibraryItemResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseInfo.GetStudentCourseInfoRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseInfo.GetStudentCourseInfoResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItemFiles.GetStudentCourseItemFilesRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItemFiles.GetStudentCourseItemFilesResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItems.GetStudentCourseItemsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseItems.GetStudentCourseItemsResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseV2.GetStudentCourseInfoV2Request
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourseV2.GetStudentCourseInfoV2Response
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourses.GetStudentCoursesRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getStudentCourses.GetStudentCoursesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.request

class CourseModule(
    override val client: HttpClient,
    override val domain: String,
    override val path: String,
) : ApiModule {

    private val modulePath = "$domain/$path"

    companion object {
        const val GET_SCHOOL_SETTINGS_REFERER_URL = "https://devrush.eduonline.io/"
        const val GET_SCHOOL_LIBRARY_ITEM_REFERER_URL = "https://devrush.eduonline.io/library"
    }

    suspend fun getStudentCourseItems(
        data: GetStudentCourseItemsRequest,
    ) = client.get("$modulePath/student-course-item") {
        header(HEADER_REFERER, "https://devrush.eduonline.io/learn/${data.studentCourseId}")
        authHeader(data)

        parameter("studentCourseId", data.studentCourseId)
        parameter("fields", data.fields)
    }.body<ApiResponse<GetStudentCourseItemsResponse>>()


    suspend fun getStudentCourse(
        data: GetStudentCourseInfoRequest,
    ) = client.get("$modulePath/student-course/${data.studentCourseId}") {
        authHeader(data)

        parameter("fields", data.fields)
    }.body<ApiResponse<GetStudentCourseInfoResponse>>()

    suspend fun getStudentCourseV2(
        data: GetStudentCourseInfoV2Request,
    ) = client.get("$modulePath/student-course") {
        authHeader(data)

        parameter("courseId", data.studentCourseId)
        parameter("fields", data.fields)
    }.body<ApiResponse<GetStudentCourseInfoV2Response>>()

    suspend fun getStudentCourses(
        data: GetStudentCoursesRequest,
    ) = client.get("$modulePath/student-course") {
        authHeader(data)

        parameter("fields", data.fields)
    }.body<ApiResponse<GetStudentCoursesResponse>>()

    suspend fun getSchoolContentItems(
        data: GetSchoolContentItemsRequest,
    ) = client.get("$modulePath/school-content-item") {
        authHeader(data)

        parameter("search", data.search)
        parameter("sectionType", data.sectionType.value)
        parameter("courseType", data.courseType.value)
        parameter("onlyAvailable", data.onlyAvailable)
    }.body<ApiResponse<GetSchoolContentItemsResponse>>()

    suspend fun getSchoolLibraryItem(
        data: GetSchoolLibraryItemRequest,
    ) = client.get("$modulePath/school-library-item") {
        header(HEADER_REFERER, "$GET_SCHOOL_LIBRARY_ITEM_REFERER_URL/$data.libraryId")
        authHeader(data)

        parameter("libraryId", data.libraryId)
        parameter("softDeleted", data.softDeleted)
        parameter("useSort", data.useSort)
        parameter("useBaseFilter", data.useBaseFilter)
        parameter("useItemsTotal", data.useItemsTotal)
        parameter("sortName", data.sortName)
        parameter("sortType", data.sortType)
        parameter("fields", data.fields)
    }.body<ApiResponse<GetSchoolLibraryItemResponse>>()

    suspend fun getSchoolLibraryInfo(
        data: GetSchoolLibraryInfoRequest,
    ) = client.get("$modulePath/school-library/${data.libraryId}") {
        header(HEADER_REFERER, "$GET_SCHOOL_LIBRARY_ITEM_REFERER_URL/$data.libraryId")
        authHeader(data)

        parameter("fields", data.fields)
    }.body<ApiResponse<GetSchoolLibraryInfoResponse>>()


    suspend fun getStudentCourseItemFiles(
        data: GetStudentCourseItemFilesRequest
    ) =  client.get("$modulePath/student-course-item/${data.studentCourseId}/${data.studentCourseItemId}") {
        authHeader(data)
    }.body<ApiResponse<GetStudentCourseItemFilesResponse>>()
}