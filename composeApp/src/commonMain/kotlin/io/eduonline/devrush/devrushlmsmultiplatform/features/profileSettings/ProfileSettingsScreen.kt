package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.internal.BackHandler
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import io.eduonline.devrush.devrushlmsmultiplatform.LocalTheme
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.ProfileSettingsViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.ProfileSettingsView
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components.ProfileSettingsTopBar
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class ProfileSettingsScreen : Screen {

    @OptIn(InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val viewModel = koinInject<ProfileSettingsViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow
        val focusManager = LocalFocusManager.current
        val localTheme = LocalTheme.current
        val onEvent: (ProfileSettingsEvent) -> Unit = remember { { viewModel.obtainEvent(it) } }
        val scope = rememberCoroutineScope()
        val snackBarHostState = remember { SnackbarHostState() }
        val singleImagePicker = rememberImagePickerLauncher(
            selectionMode = SelectionMode.Single,
            scope = scope,
            onResult = { byteArrays ->
                byteArrays.firstOrNull()?.let {
                    onEvent(ProfileSettingsEvent.ChoosePhoto(it))
                }
            }
        )

        val handleBackPress: () -> Unit = remember {
            {
                if (!viewState.isProfileEditing) navigator.pop()
                else onEvent(ProfileSettingsEvent.ShowSaveProfileWarn)
            }
        }

        BackHandler(true, handleBackPress)

        LaunchedEffect(Unit) {
            viewModel.obtainEvent(ProfileSettingsEvent.LoadProfile())
        }

        Scaffold(
            modifier = Modifier.padding(
                WindowInsets.systemBars.only(WindowInsetsSides.Top).asPaddingValues()
            ),
            topBar = {
                Column {
                    ProfileSettingsTopBar(
                        viewState,
                        navigator,
                        focusManager,
                        handleBackPress,
                        onEvent,
                    )
                    HorizontalDivider(color = DevRushTheme.colors.c4)
                }
            },
            containerColor = DevRushTheme.colors.background,
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState) {
                    Snackbar(
                        snackbarData = it,
                        contentColor = DevRushTheme.colors.c1,
                        containerColor = DevRushTheme.colors.background
                    )
                }
            }
        ) {
            when (viewState.profileLoadingState) {
                is ProfileLoadingState.Error -> ProfileSettingsView(
                    (viewState.profileLoadingState as ProfileLoadingState.Error).message
                )

                ProfileLoadingState.Loading -> ProfileSettingsView()
                ProfileLoadingState.Success -> ProfileSettingsView(
                    viewState, focusManager, Modifier.padding(it), onEvent
                )
            }
        }

        when (viewAction) {
            null -> {}
            ProfileSettingsAction.RetryLoadProfile -> {
                onEvent(ProfileSettingsEvent.LoadProfile(true))
                viewModel.clearAction()
            }

            ProfileSettingsAction.ChosePhoto -> {
                singleImagePicker.launch()
                viewModel.clearAction()
            }

            ProfileSettingsAction.DeleteAccount -> {
                navigator.replaceAll(rememberScreen(AppScreensProviders.Login))
                viewModel.clearAction()
            }

            ProfileSettingsAction.Logout -> {
                navigator.replaceAll(rememberScreen(AppScreensProviders.Splash))
                viewModel.clearAction()
            }

            is ProfileSettingsAction.ChangeTheme -> {
                localTheme.value = (viewAction as ProfileSettingsAction.ChangeTheme).value
                viewModel.clearAction()
            }

            ProfileSettingsAction.ShowSaveProfileWarn -> {
                val notSavedProfile = DevRushTheme.strings.profileNotSavedProfile
                scope.launch {
                    snackBarHostState.showSnackbar(
                        notSavedProfile,
                        duration = SnackbarDuration.Short
                    )
                    viewModel.clearAction()
                }
            }

            ProfileSettingsAction.RetrySave -> {
                onEvent(ProfileSettingsEvent.Save(true))
            }

            ProfileSettingsAction.ShowErrorComparePassword -> {
                val notConfirmPassword = DevRushTheme.strings.profileConfirmPassword
                scope.launch {
                    snackBarHostState.showSnackbar(
                        notConfirmPassword,
                        duration = SnackbarDuration.Short
                    )
                    viewModel.clearAction()
                }
            }

            ProfileSettingsAction.ShowConfirmChangeProfile -> {
                val successSave = DevRushTheme.strings.profileSuccessChangeData
                scope.launch {
                    snackBarHostState.showSnackbar(
                        successSave,
                        duration = SnackbarDuration.Short
                    )
                    viewModel.clearAction()
                }
            }

            ProfileSettingsAction.ShowErrorEmptyName -> {
                val requestField = DevRushTheme.strings.profileEmptyName
                scope.launch {
                    snackBarHostState.showSnackbar(
                        requestField,
                        duration = SnackbarDuration.Short
                    )
                    viewModel.clearAction()
                }
            }
        }
    }
}

