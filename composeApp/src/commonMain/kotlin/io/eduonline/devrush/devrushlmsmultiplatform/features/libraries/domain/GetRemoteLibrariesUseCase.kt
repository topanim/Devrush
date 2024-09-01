package io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.domain

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.libraryRepository.LibraryRepository

class GetRemoteLibrariesUseCase(
    private val libraryRepository: LibraryRepository,
) {
    suspend operator fun invoke(): List<Library> {
        val cashed = libraryRepository.getAllFromDataBase()
        val remote = libraryRepository.getAllFromServer()
        return if (cashed.isNotEmpty() && cashed == remote) cashed
        else {
            libraryRepository.saveLibrary(remote)
            remote
        }
    }
}