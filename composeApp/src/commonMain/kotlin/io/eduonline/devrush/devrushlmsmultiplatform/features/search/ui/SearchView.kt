package io.eduonline.devrush.devrushlmsmultiplatform.features.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.search_icon
import io.eduonline.devrush.devrushlmsmultiplatform.components.coursesCell.ItemCoursesCell
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.ui.ItemLibrariesCell
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model.SearchEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.search.presentation.model.SearchViewState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SearchView(state: SearchViewState, eventHandler: (SearchEvent) -> Unit) {

    val focus = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize()
            .pointerInput(state.searchQuery) {
                detectTapGestures(
                    onTap = {
                        focus.clearFocus()
                    }
                )
            }
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { eventHandler.invoke(SearchEvent.GetSearchResults(it)) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(bottom = 20.dp),
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            leadingIcon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.search_icon),
                    contentDescription = null,
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        eventHandler.invoke(SearchEvent.CloseScreenClicked)
                        eventHandler.invoke(SearchEvent.GetSearchResults(""))
                    }
                )
            },
            placeholder = {
                Text(
                    text = DevRushTheme.strings.coursesSearch,
                    style = DevRushTheme.typography.sfPro14
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = DevRushTheme.colors.c6,
                unfocusedContainerColor = DevRushTheme.colors.c6,
                focusedTextColor = DevRushTheme.colors.c2,
                unfocusedTextColor = DevRushTheme.colors.c2,
                focusedBorderColor = DevRushTheme.colors.c5,
                unfocusedBorderColor = DevRushTheme.colors.c5,
                focusedLeadingIconColor = DevRushTheme.colors.c3,
                unfocusedLeadingIconColor = DevRushTheme.colors.c3,
                focusedTrailingIconColor = DevRushTheme.colors.c3,
                unfocusedTrailingIconColor = DevRushTheme.colors.c3,
                focusedPlaceholderColor = DevRushTheme.colors.c2,
                unfocusedPlaceholderColor = DevRushTheme.colors.c2
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions {
                focus.clearFocus()
            },
        )

        LazyColumn {
            when {
                state.coursesResults.isNotEmpty() -> {
                    items(state.coursesResults) {
                        Gap(10)
                        ItemCoursesCell(it) {
                            eventHandler.invoke(
                                SearchEvent.ClickItemCourse(
                                    studentCourseId = it.studentCourse.id,
                                    courseItemId = it.studentCourse.currentCourseItem.id,
                                    imageCloudKey = it.primaryImageCloudKey ?: ""
                                )
                            )
                        }
                    }
                }

                state.librariesResults.isNotEmpty() -> {
                    items(state.librariesResults) {
                        Gap(10)
                        ItemLibrariesCell(
                            it,
                            Modifier.fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    eventHandler.invoke(
                                        SearchEvent.ClickItemLibrary(
                                            libraryId = it.id,
                                            imageCloudKey = it.primaryImageCloudKey
                                        )
                                    )
                                }
                                .background(DevRushTheme.colors.c6)
                                .padding(10.dp)
                        )
                    }
                }

                else -> {}
            }
        }
    }
}