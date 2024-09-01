package io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.domain


import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.libraryRepository.LibraryRepository

class GetCachedLibrariesUseCase(
    private val libraryRepository: LibraryRepository,
) {
    suspend operator fun invoke(): List<Library> {
        return libraryRepository.getAllFromDataBase()
    }
}