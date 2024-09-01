package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme

sealed interface ProfileSettingsAction {
    data object ChosePhoto : ProfileSettingsAction
    data object Logout : ProfileSettingsAction
    data object DeleteAccount : ProfileSettingsAction
    data object ShowSaveProfileWarn : ProfileSettingsAction
    data object ShowErrorComparePassword : ProfileSettingsAction
    data object ShowConfirmChangeProfile:ProfileSettingsAction
    data object ShowErrorEmptyName:ProfileSettingsAction

    data object RetryLoadProfile : ProfileSettingsAction
    data object RetrySave : ProfileSettingsAction

    data class ChangeTheme(val value: Theme) : ProfileSettingsAction
}