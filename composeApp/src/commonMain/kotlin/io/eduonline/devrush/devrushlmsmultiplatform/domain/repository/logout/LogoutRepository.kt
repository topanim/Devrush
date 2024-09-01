package io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.logout

import io.eduonline.devrush.devrushlmsmultiplatform.di.viewModels
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.courseRepository.CourseRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.libraryRepository.LibraryRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class LogoutRepository : KoinComponent {

    val courseRepository: CourseRepository by inject()
    val libraryRepository: LibraryRepository by inject()
    val profileRepository: ProfileRepository by inject()

    suspend fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            courseRepository.cleanCourses()
            libraryRepository.cleanLibrary()
            profileRepository.cleanProfiles()
            profileRepository.cleanLeader()
            profileRepository.cleanAchievements()
        }

        unloadKoinModules(viewModels)
        loadKoinModules(viewModels)
    }
}