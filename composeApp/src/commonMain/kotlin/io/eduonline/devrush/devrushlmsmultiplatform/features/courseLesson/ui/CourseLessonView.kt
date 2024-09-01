package io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.WebViewState
import com.multiplatform.webview.web.rememberWebViewNavigator
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.presentation.model.CourseLessonState
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.ui.components.FilesSheet
import io.eduonline.devrush.devrushlmsmultiplatform.features.courseLesson.ui.components.LessonFooter
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme.strings
import io.eduonline.devrush.devrushlmsmultiplatform.utils.takeDot
import kotlinx.coroutines.launch


@Composable
fun CourseLessonView(
    state: CourseLessonState,
    action: CourseLessonAction?,
    webViewState: WebViewState,
    onEvent: (CourseLessonEvent) -> Unit,
    clearAction: () -> Unit
) {
    val webViewNavigator = rememberWebViewNavigator()
    val navigator = LocalNavigator.currentOrThrow
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val insets = WindowInsets.systemBars.only(WindowInsetsSides.Vertical)

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) {
                Snackbar(
                    snackbarData = it,
                    contentColor = DevRushTheme.colors.c1,
                    containerColor = DevRushTheme.colors.background
                )
            }
        },
        modifier = Modifier.padding(insets.asPaddingValues()),
        contentWindowInsets = insets,
        topBar = {
            DevRushTopAppBar(
                webViewState.pageTitle ?: "",
                onBackClick = navigator::pop
            )
        },
        bottomBar = { LessonFooter(onEvent) }
    ) {
        if (state.showFilesBottomSheet)
            FilesSheet(state, onEvent)

        Column {
            WebView(
                state = webViewState,
                navigator = webViewNavigator,
                modifier = Modifier.padding(it).fillMaxSize()
            )
        }
    }

    when (action) {
        null -> Unit
        is CourseLessonAction.FileLoaded -> {
            clearAction()
            val message = strings.courseLessonFileDownloaded
            scope.launch {
                snackBarHostState
                    .showSnackbar(message = "$message - ${action.name.takeDot(20)}")
            }
        }

        is CourseLessonAction.FileLoading -> {
            clearAction()
            val message = strings.courseLessonFileDownloading
            scope.launch {
                snackBarHostState
                    .showSnackbar(message = "$message - ${action.name.takeDot(20)}")
            }
        }

        is CourseLessonAction.FileNotLoaded -> {
            clearAction()
            val message = strings.courseLessonFileNotDownloaded
            scope.launch {
                snackBarHostState
                    .showSnackbar(message = "$message - ${action.name.takeDot(20)}")
            }
        }
    }

    if (!webViewState.isLoading) {
        webViewNavigator.evaluateJavaScript(
            """
                function waitForElementToDisplay(selector, time) {
                    if(document.querySelector(selector) != null) {
                        document.querySelector(selector).style.display = 'none';
                        console.log("Header hidden successfully");
                    } else {
                        setTimeout(function() {
                            waitForElementToDisplay(selector, time);
                        }, time);
                    }
                }
                waitForElementToDisplay('.ant-layout-header._header_1pv17_1', 100);
                waitForElementToDisplay('._header_1f8yi_15', 100);
                waitForElementToDisplay('._item_content_footer_t8lnz_1', 100);
                """
        )
    }
}