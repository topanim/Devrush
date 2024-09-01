package io.eduonline.devrush.devrushlmsmultiplatform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.AppSettingsRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.AccessesPaymentsScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.AchievementsScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.CourseLessonScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.DetailCourseScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.forgotPassword.ForgotPasswordScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.LeaderboardScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.login.LoginScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.onboarding.OnboardingScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ProfileSettingsScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.RegistrationScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.SearchScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.splash.SplashScreen
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.main.MainScreen
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.main.mainScreenModule
import io.eduonline.devrush.devrushlmsmultiplatform.theme.AppTheme
import org.koin.compose.koinInject


internal val LocalAppSettings = staticCompositionLocalOf<AppSettingsRepository> {
    error("No settings repository provided")
}

internal val LocalTheme = staticCompositionLocalOf<MutableState<Theme>> {
    mutableStateOf(Theme.System)
}

@Composable
internal fun DevRushLMSApp() {
    val settingsRepository = koinInject<AppSettingsRepository>()
    val currentTheme = LocalTheme.current

    LaunchedEffect(Unit) {
        currentTheme.value = settingsRepository.getSettings().theme()
        Logger.d("d") { "current theme: ${currentTheme.value}" }
    }

    CompositionLocalProvider(
        LocalAppSettings provides settingsRepository,
        LocalTheme provides currentTheme
    ) {
        AppTheme(theme = currentTheme.value) {
            ScreenRegistry {
                register<AppScreensProviders.Splash> { SplashScreen() }
                register<AppScreensProviders.Login> { LoginScreen() }
                register<AppScreensProviders.Registration> { RegistrationScreen() }
                register<AppScreensProviders.ForgotPassword> { ForgotPasswordScreen() }
                register<AppScreensProviders.Achievement> { AchievementsScreen() }
                register<AppScreensProviders.Main> { MainScreen() }
                register<AppScreensProviders.ProfileSettings> { ProfileSettingsScreen() }
                register<AppScreensProviders.Leaderboard> { LeaderboardScreen() }
                register<AppScreensProviders.AccessesPayments> { AccessesPaymentsScreen() }
                register<AppScreensProviders.OnboardingScreen> { OnboardingScreen() }

                register<AppScreensProviders.DetailCourse> {
                    DetailCourseScreen(it.studentCourseId, it.courseItemId, it.imageCloudKey)
                }

                register<AppScreensProviders.Lesson> {
                    CourseLessonScreen(it.lessonId, it.courseId, it.pageType)
                }

                register<AppScreensProviders.Search> {
                    SearchScreen(it.courses, it.libraries)
                }

                mainScreenModule()
            }

            Navigator(rememberScreen(AppScreensProviders.Splash)) {
                SlideTransition(it)
            }
        }
    }
}