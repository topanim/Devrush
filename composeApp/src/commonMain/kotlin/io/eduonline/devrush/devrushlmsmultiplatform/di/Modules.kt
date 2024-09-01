package io.eduonline.devrush.devrushlmsmultiplatform.di

import androidx.room.RoomDatabase
import io.eduonline.devrush.devrushlmsmultiplatform.database.AppDatabase
import io.eduonline.devrush.devrushlmsmultiplatform.database.getRoomDatabase
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.DevRushApi
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.AuthModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.ChatModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.CourseModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.GamificationModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.localization.LocalizationModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.NotificationModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.quiz.QuizModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.courseRepository.CourseRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.libraryRepository.LibraryRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.logout.LogoutRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.notification.NotificationRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository.ProfileRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.AppSettingsRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.AllValidation
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidEmailUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidPasswordUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.GetAccessesDataUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.GetOrdersDataUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.GetSubscriptionsDataUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.UpdateSubscriptionUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.AccessesPaymentsViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.AchievementsViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.ChatViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.domain.DownloadFileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.domain.GetCourseItemFilesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.CourseLessonViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain.GetCachedCoursesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain.GetRemoteCoursesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain.PushMarkAllAsReadUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.presentation.CoursesViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.GetCourseInfoUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.domain.GetLibraryInfoUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.DetailCourseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.domain.ForgotPasswordUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.presentation.ForgotPasswordViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.presentation.LeaderboardViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.domain.GetCachedLibrariesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.domain.GetRemoteLibrariesUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.presentation.LibraryViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.domain.IsUserAuthorizedUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.presentation.LoginViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.presentation.OnboardingViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.achievements.GetCachedAchievementsUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.achievements.GetRemoteAchievementsUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.leader.GetCachedLeaderboardUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.leader.GetRemoteLeaderboardUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.profile.GetCachedProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.profile.GetRemoteProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.ProfileScreenViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain.GetLoginUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain.LogoutUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain.SaveProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain.UploadFileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.ProfileSettingsViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.domain.SaveNameProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.RegistrationViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.SearchViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.splash.SplashViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            commonModule(),
            useCases,
            viewModels,
            repositories,
            apiModules
        )
    }


@OptIn(ExperimentalSerializationApi::class)
fun commonModule() = module {
    single<Json> {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
            decodeEnumsCaseInsensitive = true
            explicitNulls = true
        }
    }

    single<HttpClient> {
        HttpClient(get()) {
            install(WebSockets)
            install(HttpTimeout)
            install(ContentNegotiation) { json(get()) }
            followRedirects = false
        }
    }

    single {
        getRoomDatabase(
            get<RoomDatabase.Builder<AppDatabase>>()
        )
    }
}

val repositories = module {
    singleOf(::LogoutRepository)
    single<AuthTokenRepository> { AuthTokenRepository(get(), get(named("auth_storage"))) }
    single<AppSettingsRepository> { AppSettingsRepository(get(named("app_settings"))) }
    singleOf(::CourseRepository)
    singleOf(::NotificationRepository)
    singleOf(::LibraryRepository)
    singleOf(::ProfileRepository)
}

val apiModules = module {
    single<ChatModule> { ChatModule(get(), get(), get()) }
    single<AuthModule> { AuthModule(get(), DevRushApi.DOMAIN, DevRushApi.PATH) }
    single<CourseModule> { CourseModule(get(), DevRushApi.DOMAIN, DevRushApi.PATH) }
    single<QuizModule> { QuizModule(get(), DevRushApi.DOMAIN, DevRushApi.PATH) }
    single<FileModule> { FileModule(get(), DevRushApi.DOMAIN, DevRushApi.PATH) }
    single<StudentModule> { StudentModule(get(), DevRushApi.DOMAIN, DevRushApi.PATH) }
    single<GamificationModule> { GamificationModule(get(), DevRushApi.DOMAIN, DevRushApi.PATH) }
    single<NotificationModule> { NotificationModule(get(), DevRushApi.DOMAIN, DevRushApi.PATH) }
    single<LocalizationModule> { LocalizationModule(get(), DevRushApi.DOMAIN, DevRushApi.PATH) }

}

val useCases = module {
    singleOf(::LogoutUseCase)

    singleOf(::SaveProfileUseCase)
    singleOf(::UploadFileUseCase)
    singleOf(::GetLoginUseCase)
    singleOf(::DownloadFileUseCase)

    singleOf(::IsUserAuthorizedUseCase)
    singleOf(::ForgotPasswordUseCase)

    singleOf(::ValidEmailUseCase)
    singleOf(::ValidPasswordUseCase)
    singleOf(::AllValidation)

    singleOf(::GetCachedCoursesUseCase)
    singleOf(::GetRemoteCoursesUseCase)
    singleOf(::GetCachedLibrariesUseCase)
    singleOf(::GetRemoteLibrariesUseCase)
    singleOf(::GetCachedProfileUseCase)
    singleOf(::GetRemoteProfileUseCase)
    singleOf(::GetCachedProfileUseCase)
    singleOf(::GetCachedAchievementsUseCase)
    singleOf(::GetRemoteAchievementsUseCase)
    singleOf(::GetCachedLeaderboardUseCase)
    singleOf(::GetRemoteLeaderboardUseCase)
    singleOf(::GetSubscriptionsDataUseCase)
    singleOf(::PushMarkAllAsReadUseCase)
    singleOf(::SaveNameProfileUseCase)

    singleOf(::GetCourseInfoUseCase)
    singleOf(::GetLibraryInfoUseCase)
    singleOf(::GetCourseItemFilesUseCase)

    singleOf(::GetOrdersDataUseCase)
    singleOf(::GetSubscriptionsDataUseCase)
    singleOf(::GetAccessesDataUseCase)
    singleOf(::UpdateSubscriptionUseCase)
}

val viewModels = module {
    singleOf(::AccessesPaymentsViewModel)
    singleOf(::DetailCourseViewModel)
    singleOf(::CoursesViewModel)
    factoryOf(::CourseLessonViewModel)
    singleOf(::AchievementsViewModel)
    singleOf(::LoginViewModel)
    singleOf(::SplashViewModel)
    singleOf(::RegistrationViewModel)
    singleOf(::ForgotPasswordViewModel)
    singleOf(::ProfileSettingsViewModel)
    singleOf(::LibraryViewModel)
    singleOf(::LeaderboardViewModel)
    singleOf(::SearchViewModel)
    singleOf(::OnboardingViewModel)
    singleOf(::ProfileScreenViewModel)
    singleOf(::ChatViewModel)
}

