package io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation

import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model.SearchAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model.SearchEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model.SearchViewState
import org.koin.core.component.KoinComponent

class SearchViewModel : BaseViewModel<SearchViewState, SearchAction, SearchEvent>(
    SearchViewState()
), KoinComponent {

    override fun obtainEvent(viewEvent: SearchEvent) {
        when (viewEvent) {
            SearchEvent.CloseScreenClicked -> viewAction = SearchAction.CloseScreen
            is SearchEvent.GetCourse -> viewState = viewState.copy(courses = viewEvent.courses)
            is SearchEvent.GetLibrary -> viewState = viewState.copy(libraries = viewEvent.libraries)
            is SearchEvent.GetSearchResults -> getSearchResults(viewEvent.query, viewEvent.retried)
            is SearchEvent.ClickItemCourse -> viewAction = SearchAction.PresentDetailCourse(
                courseItemId = viewEvent.courseItemId,
                studentCourseId = viewEvent.studentCourseId,
                imageCloudKey = viewEvent.imageCloudKey
            )

            is SearchEvent.ClickItemLibrary -> viewAction = SearchAction.PresentDetailLibrary(
                libraryId = viewEvent.libraryId,
                imageCloudKey = viewEvent.imageCloudKey
            )
        }
    }

    private fun getSearchResults(query: String, retried: Boolean) {
        viewState = viewState.copy(
            searchQuery = query,
            coursesResults = viewState.courses.filter {
                it.title?.contains(
                    query,
                    ignoreCase = true
                ) == true
            },
            librariesResults = viewState.libraries.filter {
                it.title?.contains(
                    query,
                    ignoreCase = true
                ) == true
            }
        )
    }
}