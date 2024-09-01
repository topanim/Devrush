package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain

import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.common.FileType.Image
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.beginUpload.BeginUploadRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.completeUpload.CompleteUploadRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.completeUpload.ETag
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.uploadFile.UploadFileRequest
import io.eduonline.devrush.devrushlmsmultiplatform.utils.getRandomString

class UploadFileUseCase(
    private val api: FileModule,
    private val tokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(file: ByteArray): Pair<String, String> {
        val accessToken = tokenRepository.getTokens().accessToken
        val begin = api.beginUpload(
            BeginUploadRequest(
                accessToken,
                file.size,
                getRandomString(6),
                Image,
                ".jpeg"
            )
        ).checkedRefresh(tokenRepository).checkedResult()

        Logger.d("d") { begin.toString() }

        val upload = api.uploadFile(
            UploadFileRequest(begin.urls.first(), file)
        )

        Logger.d("d") { upload.toString() }

        val complete = api.completeUpload(
            CompleteUploadRequest(
                accessToken,
                listOf(
                    ETag(
                        upload.eTag,
                        upload.partNumber
                    )
                ),
                begin.id
            )
        ).checkedRefresh(tokenRepository).checkedResult()

        Logger.d("d") { complete.toString() }

        return complete.id to complete.cloudKey
    }
}