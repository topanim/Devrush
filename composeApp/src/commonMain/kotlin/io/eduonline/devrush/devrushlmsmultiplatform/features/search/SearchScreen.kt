package io.eduonline.devrush.devrushlmsmultiplatform.features.search

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
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Course
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.course.models.getSchoolContentItems.Library
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.SearchViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model.SearchAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model.SearchEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.ui.SearchView
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.koin.compose.koinInject

data class SearchScreen(
    val courses: List<Course>?,
    val libraries: List<Library>?,
) : Screen {

    @Composable
    override fun Content() {
        val viewModel: SearchViewModel = koinInject<SearchViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.padding(
                WindowInsets.systemBars.only(WindowInsetsSides.Vertical).asPaddingValues()
            )
        ) {
            DevRushTopAppBar(DevRushTheme.strings.coursesSearch, backButton = false)
            SearchView(viewState) { viewModel.obtainEvent(it) }
        }

        LaunchedEffect(Unit) {
            courses?.let {
                viewModel.obtainEvent(SearchEvent.GetCourse(it))
                viewModel.obtainEvent(SearchEvent.GetSearchResults(viewState.searchQuery))
            }
            libraries?.let {
                viewModel.obtainEvent(SearchEvent.GetLibrary(it))
                viewModel.obtainEvent(SearchEvent.GetSearchResults(viewState.searchQuery))
            }
        }

        when (viewAction) {
            null -> {}
            SearchAction.CloseScreen -> {
                navigator.pop()
                viewModel.clearAction()
            }

            is SearchAction.PresentDetailCourse -> {
                val castData = viewAction as SearchAction.PresentDetailCourse
                val detailCourseScreen = rememberScreen(
                    AppScreensProviders.DetailCourse(
                        castData.studentCourseId,
                        castData.courseItemId,
                        castData.imageCloudKey
                    )
                )
                navigator.push(detailCourseScreen)
                viewModel.clearAction()
            }

            is SearchAction.PresentDetailLibrary -> {
                val castData = viewAction as SearchAction.PresentDetailLibrary
                val detailCourseScreen = rememberScreen(
                    AppScreensProviders.DetailCourse(
                        studentCourseId = castData.libraryId,
                        courseItemId = null,
                        imageCloudKey = castData.imageCloudKey
                    )
                )
                navigator.push(detailCourseScreen)
                viewModel.clearAction()
            }

            is SearchAction.RetryGetSearchResults -> {
                val castData = viewAction as SearchAction.RetryGetSearchResults
                viewModel.obtainEvent(SearchEvent.GetSearchResults(castData.query, true))
                viewModel.clearAction()
            }
        }
    }
}