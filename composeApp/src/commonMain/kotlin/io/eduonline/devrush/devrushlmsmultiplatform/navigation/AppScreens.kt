package io.eduonline.devrush.devrushlmsmultiplatform.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.PageType

sealed interface AppScreensProviders : ScreenProvider {

    data object Achievement : AppScreensProviders
    data object Splash : AppScreensProviders
    data object Login : AppScreensProviders
    data object Registration : AppScreensProviders
    data object ForgotPassword : AppScreensProviders
    data object Main : AppScreensProviders
    data object OnboardingScreen : AppScreensProviders
    data object Courses : AppScreensProviders
    data object Libraries : AppScreensProviders
    data object Chat : AppScreensProviders
    data object Profile : AppScreensProviders
    data object ProfileSettings : AppScreensProviders
    data object Leaderboard : AppScreensProviders
    data object AccessesPayments : AppScreensProviders

    data class Search(
        val courses: List<Course>?,
        val libraries: List<Library>?,
    ) : AppScreensProviders

    data class Lesson(
        val lessonId: String,
        val courseId: String,
        val pageType: PageType,
    ) : AppScreensProviders

    data class DetailCourse(
        val studentCourseId: String,
        val courseItemId: String?,
        val imageCloudKey: String?,
    ) : AppScreensProviders
}