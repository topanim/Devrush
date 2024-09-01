package io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.DetailCourseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.DetailCourseLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.presentation.models.PageType
import io.eduonline.devrush.devrushlmsmultiplatform.features.detailCourse.ui.DetailsCourseView
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.koin.compose.koinInject

data class DetailCourseScreen(
    val studentCourseId: String,
    val courseItemId: String?,
    val imageCloudKey: String?,
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinInject<DetailCourseViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow
        var stepByStep by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            if (courseItemId != null) {
                stepByStep = true
                viewModel.obtainEvent(DetailCourseEvent.SetPageType(PageType.CURSES))
                viewModel.obtainEvent(
                    DetailCourseEvent.GetCourse(
                        studentCourseId,
                        courseItemId,
                        imageCloudKey
                    )
                )

            } else {
                stepByStep = false
                viewModel.obtainEvent(DetailCourseEvent.SetPageType(PageType.LIBRARY))
                viewModel.obtainEvent(
                    DetailCourseEvent.GetLibrary(
                        studentCourseId,
                        imageCloudKey
                    )
                )
            }

        }


        Column(
            modifier = Modifier.padding(
                WindowInsets.systemBars.only(WindowInsetsSides.Vertical).asPaddingValues()
            )
        ) {
            DevRushTopAppBar(
                when (viewState.pageType) {
                    PageType.CURSES -> DevRushTheme.strings.screenNameCourseInfo
                    PageType.LIBRARY -> DevRushTheme.strings.screenNameLibraryInfo
                    else -> ""
                }

            ) { navigator.pop() }

            when (viewState.fetchCourseInfoRequest) {
                DetailCourseLoadingState.Loading -> DetailsCourseView()

                is DetailCourseLoadingState.Error -> DetailsCourseView(
                    (viewState.fetchCourseInfoRequest as DetailCourseLoadingState.Error).text
                )

                is DetailCourseLoadingState.Success -> DetailsCourseView(
                    viewState.fetchCourseInfoRequest as DetailCourseLoadingState.Success,
                    stepByStep
                ) { viewModel.obtainEvent(it) }
            }
        }

        when (viewAction) {
            null -> {}
            is DetailCourseAction.OpenLesson -> {
                val openLesson = viewAction as DetailCourseAction.OpenLesson
                val lessonScreen = rememberScreen(
                    AppScreensProviders.Lesson(
                        lessonId = openLesson.lessonId,
                        courseId = studentCourseId,
                        pageType = viewState.pageType
                    )
                )
                navigator.push(lessonScreen)
                viewModel.clearAction()
            }

            DetailCourseAction.OpenCourseCertificate -> {}
            else -> {}
        }
    }
}
