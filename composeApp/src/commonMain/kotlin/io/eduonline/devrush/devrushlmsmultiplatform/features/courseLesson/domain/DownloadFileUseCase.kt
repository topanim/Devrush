package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.domain

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.models.get.GetFileRequest

class DownloadFileUseCase(
    private val fileModule: FileModule,
    private val tokenRepository: AuthTokenRepository
) {
    suspend operator fun invoke(
        id: String,
        name: String,
        extension: String,
    ) {
        val fileUrl = fileModule.get(
            GetFileRequest(
                token = tokenRepository.accessToken(),
                id = id,
                isPreview = false
            )
        ).checkedRefresh(tokenRepository).checkedResult()

        fileModule.downloadFile(fileUrl, name, extension)
    }
}