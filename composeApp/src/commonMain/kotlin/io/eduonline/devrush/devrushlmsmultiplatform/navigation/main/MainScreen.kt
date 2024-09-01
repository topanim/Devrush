package io.eduonline.devrush.devrushlmsmultiplatform.navigation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ChatScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.ChatViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.courses.CoursesScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.libraries.LibrariesScreen
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ProfileScreen
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.koin.compose.koinInject

val mainScreenModule = screenModule {
    register<AppScreensProviders.Courses> {
        CoursesScreen
    }
    register<AppScreensProviders.Libraries> {
        LibrariesScreen
    }
    register<AppScreensProviders.Chat> {
        ChatScreen
    }
    register<AppScreensProviders.Profile> {
        ProfileScreen
    }

}

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val tabs = listOf(CoursesScreen, LibrariesScreen, ChatScreen, ProfileScreen)
        val viewModel = koinInject<ChatViewModel>()

        LaunchedEffect(Unit) {
            viewModel.obtainEvent(viewEvent = ChatEvent.InitConnection)
        }

        TabNavigator(
            CoursesScreen,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = tabs
                )
            }
        ) {
            Scaffold(
                containerColor = DevRushTheme.colors.background,
                bottomBar = {
                    Column {
                        HorizontalDivider(color = DevRushTheme.colors.c5)
                        NavigationBar(
                            containerColor = Color.Transparent
                        ) {
                            tabs.forEach { TabNavigationItem(it) }
                        }
                    }
                }
            ) {
                Column(
                    Modifier.padding(
                        top = it.calculateTopPadding(),
                        bottom = it.calculateBottomPadding()
                    )
                ) {
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        label = { Text(text = tab.options.title) },
        icon = {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
            selectedIconColor = DevRushTheme.colors.blue1,
            selectedTextColor = DevRushTheme.colors.blue1,
            unselectedIconColor = DevRushTheme.colors.c3,
            unselectedTextColor = DevRushTheme.colors.c3
        )
    )
}