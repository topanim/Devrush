package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file


import io.eduonline.devrush.devrushlmsmultiplatform.di.downloadAndSave
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.HEADER_REFERER
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.X_CHUNK_SIZE
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.X_CONTENT_LENGTH
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.X_CONTENT_NAME
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.X_CONTENT_TYPE
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.ApiHelper.X_IS_PUBLIC
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.helpers.authHeader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.beginUpload.BeginUploadRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.beginUpload.BeginUploadResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.completeUpload.CompleteUploadRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.completeUpload.CompleteUploadResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.get.GetFileRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.prepareUpload.PrepareUploadRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.prepareUpload.PrepareUploadResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.uploadFile.UploadFileRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.uploadFile.UploadFileResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.datetime.Clock

class FileModule(
    override val client: HttpClient,
    override val domain: String,
    override val path: String,
) : ApiModule {
    private val modulePath = "$domain/$path/$MODULE"

    companion object {
        const val MODULE = "file"
        private const val FILE_SERVER_PATH = "https://accelfiles.s3.eu-central-1.amazonaws.com/"

        const val BEGIN_UPLOAD_REFERER = "https://devrush.eduonline.io/"
        const val PREPARE_REFERER = "https://devrush.eduonline.io"
        const val COMPLETE_REFERER = "https://devrush.eduonline.io/"

        fun getFullFilePath(cloudKey: String) = FILE_SERVER_PATH + cloudKey
    }

    suspend fun beginUpload(
        data: BeginUploadRequest,
    ) = client.post("$modulePath/begin") {
        header(HEADER_REFERER, BEGIN_UPLOAD_REFERER)
        authHeader(data)

        header(X_CHUNK_SIZE, data.chunkSize)
        header(X_CONTENT_LENGTH, data.contentSize)
        header(X_CONTENT_NAME, data.fileFullName)
        header(X_CONTENT_TYPE, data.contentType)
        header(X_IS_PUBLIC, "true")
    }.body<ApiResponse<BeginUploadResponse>>()

    suspend fun uploadFile(
        data: UploadFileRequest,
    ): UploadFileResponse {
        val response = client.put(data.url) {
            contentType(ContentType.Application.OctetStream)
            setBody(data.file)
        }

        return UploadFileResponse(
            response.headers["Etag"]!!,
            response.request.url.parameters["partNumber"]!!.toInt()
        )
    }

    suspend fun prepareUpload(
        data: PrepareUploadRequest,
    ) = client.put("$modulePath/prepare/${data.id}") {
        header(HEADER_REFERER, PREPARE_REFERER)
    }.body<ApiResponse<PrepareUploadResponse>>()

    suspend fun completeUpload(
        data: CompleteUploadRequest,
    ) = client.post("$modulePath/complete") {
        header(HEADER_REFERER, COMPLETE_REFERER)
        authHeader(data)

        contentType(ContentType.Application.Json)
        setBody(data)
    }.body<ApiResponse<CompleteUploadResponse>>()

    suspend fun get(
        data: GetFileRequest,
    ) = client.get("$modulePath/${data.id}") {
        header(HEADER_REFERER, COMPLETE_REFERER)
        authHeader(data)

        parameter("isPreview", data.isPreview)
    }.body<ApiResponse<String>>()

    suspend fun downloadFile(url: String, name: String?, extension: String) {
        client.downloadAndSave(
            url,
            (name ?: "downloaded_file") + "_" +
                    Clock.System.now().toEpochMilliseconds().toString() +
                    extension
        )
    }
}