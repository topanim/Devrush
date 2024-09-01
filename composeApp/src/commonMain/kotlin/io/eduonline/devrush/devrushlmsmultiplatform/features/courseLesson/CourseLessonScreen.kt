package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.intl.Locale
import cafe.adriel.voyager.core.screen.Screen
import co.touchlab.kermit.Logger
import com.multiplatform.webview.cookie.Cookie
import com.multiplatform.webview.web.rememberWebViewState
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.DevRushApi
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.CourseLessonViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.ui.CourseLessonView
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.PageType
import org.koin.compose.koinInject

data class CourseLessonScreen(
    val lessonId: String,
    val courseId: String,
    val pageType: PageType,
) : Screen {

    @Composable
    override fun Content() {
        val authRepository: AuthTokenRepository = koinInject()

        val viewModel: CourseLessonViewModel = koinInject()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val onEvent = remember { { event: CourseLessonEvent -> viewModel.obtainEvent(event) } }

        val lessonUrl = if (pageType == PageType.CURSES)
            "https://devrush.eduonline.io/learn/$lessonId/theory"
        else "https://devrush.eduonline.io/library/$courseId/$lessonId"


        val webViewState = rememberWebViewState(lessonUrl)

        webViewState.webSettings.apply {
            isJavaScriptEnabled = true
            androidWebSettings.domStorageEnabled = true
            androidWebSettings.loadsImagesAutomatically = true
            androidWebSettings.allowFileAccess = true
            androidWebSettings.isAlgorithmicDarkeningAllowed = true
        }

        LaunchedEffect(Unit) {
            Logger.d("d") { "Launched" }
            when (pageType) {
                PageType.CURSES -> onEvent(
                    CourseLessonEvent.FetchFiles(courseId, lessonId)
                )
//                PageType.LIBRARY -> {}
                else -> {}
            }
            val tokens = authRepository.getTokens()

            webViewState.cookieManager.apply {
                setCookie(
                    DevRushApi.DOMAIN,
                    Cookie("student_refresh_token", tokens.refreshToken)
                )
                setCookie(
                    DevRushApi.DOMAIN,
                    Cookie("student_access_token", tokens.accessToken)
                )
                setCookie(
                    DevRushApi.DOMAIN,
                    Cookie("loc", Locale.current.language)
                )
                setCookie(
                    DevRushApi.DOMAIN,
                    Cookie("country", Locale.current.region)
                )
            }
        }


        CourseLessonView(
            viewState,
            viewAction,
            webViewState,
            onEvent,
            viewModel::clearAction
        )
    }
}
