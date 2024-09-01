package io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.libraryRepository

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.database.AppDatabase
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.LibraryDBO
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.CourseModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.GetSchoolContentItemsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.SectionType

class LibraryRepository(
    private val api: CourseModule,
    private val database: AppDatabase,
    private val tokenRepository: AuthTokenRepository,
) {
    suspend fun getAllFromServer(): List<Library> {
        val schoolContentItems = api.getSchoolContentItems(
            GetSchoolContentItemsRequest(
                tokenRepository.getTokens().accessToken,
                SectionType.Library
            )
        ).checkedRefresh(tokenRepository).checkedResult().items

        return schoolContentItems.map { it.library!! }
    }

    suspend fun getAllFromDataBase(): List<Library> {
        val cashedLibrary = database.getLibraryDao().getAll().map { it.toLibrary() }
        return cashedLibrary.ifEmpty { emptyList() }
    }

    suspend fun saveLibrary(library: List<Library>) {
        val libraryDBO = library.map { it.toLibraryDBO() }
        database.getLibraryDao().insert(libraryDBO)
    }

    suspend fun deleteLibrary(library: List<LibraryDBO>) {
        database.getLibraryDao().delete(library)
    }

    suspend fun cleanLibrary() {
        database.getLibraryDao().clean()
    }
}